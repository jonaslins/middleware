package jms;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Broker implements Runnable{

	private ServerSocket serverSocket;
	private int port;
	private Thread publishThread ;
	private Hashtable<String, TopicContext> hashtable;
	private Thread consumerThread ;
	public Broker(int port) throws IOException {
		this.port = port;
		serverSocket = new ServerSocket(port);
		hashtable = new Hashtable<String, TopicContext>();

	}

	public static void main(String[] args) throws Exception {

		Broker receiver = new Broker(8080);
		Thread threadReceiver = new Thread(receiver);
		threadReceiver.start();
	}

	@Override
	public void run() {

		while(true){
			//inicia uma nova conexão
			Socket connectionSocket;
			try {
				connectionSocket = serverSocket.accept(); 
				MessageHandler smh = new MessageHandler(connectionSocket);
				InterMessage interMessage = (InterMessage) Marshaller.interUnmarshall(smh.receive());
				String tipo ="susbcriber";
				if(interMessage.getJMSType()==1){
					tipo = "publisher";     
				}else if(interMessage.getJMSType()==3){
					tipo = "listarTopicos";
				}
				System.out.println("Broker recebeu uma mensagem de um "+tipo);
				String topicName = interMessage.getJMSDestination();
				if(interMessage.getJMSType()==2){//subscriber
					TopicContext topicContext = hashtable.get(topicName);
					String hostName = connectionSocket.getInetAddress().getHostName();
					topicContext.getSubscribers().add(new Subscriber(hostName, connectionSocket.getPort(),connectionSocket));

				}else if(interMessage.getJMSType()==1){ //publish
					TopicContext topicContext = null;
					if(!hashtable.containsKey(topicName)){
						hashtable.put(topicName, new TopicContext(topicName));
						topicContext = hashtable.get(topicName);
						TopicSender topicSender = new TopicSender(topicContext);
						consumerThread = new Thread(topicSender);
						consumerThread.start();
					}else{
						topicContext = hashtable.get(topicName);
					}

					publishThread  = new ConnectionPublisher(connectionSocket, topicContext);
					publishThread.start();

				}else if(interMessage.getJMSType()==3){ //listar topicos
					List<String> chaves = new ArrayList<String>(hashtable.keySet());
					String topics ="";
					for(String c:chaves){
						topics += c+",";
					}
					InterMessage mensagem = new InterMessage();
					mensagem.setTextMessage(topics);
					smh.send(Marshaller.marshall(mensagem));
					connectionSocket.close();
				}
			}catch (Exception e) {
				System.out.println("erros");
			}

		}
	}
}


