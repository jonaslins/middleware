package jms;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

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
				ServerMessageHandler smh = new ServerMessageHandler(connectionSocket);
				InterMessage message1 = (InterMessage) Marshaller.interUnmarshall(smh.receive());
				String tipo ="susbcriber";
				if(message1.getJMSType()==1){
					tipo = "publisher";					
				}
				System.out.println("Broker recebeu uma mensagem de um "+tipo);
				String topicName = message1.getJMSDestination();
				if(message1.getJMSType()==2){//subscriber
					TopicContext topicContext = hashtable.get(topicName);
					String hostName = connectionSocket.getInetAddress().getHostName();
					topicContext.getSubscribers().add(new Subscriber(hostName, connectionSocket.getPort(),connectionSocket));
					
				}else{ //publish
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
					
					publishThread  = new ConnectionPublish(connectionSocket, topicContext);
					publishThread.start();
					
				}
			}catch (Exception e) {
				System.out.println("erros");
			}

		}
	}
}


