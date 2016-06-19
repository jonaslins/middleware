package jms;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class BrokerReceiver implements Runnable{
	
	private ServerSocket serverSocket;
	private int port;
	
	private Hashtable<String, TopicContext> hashtable;
	
	public BrokerReceiver(int port) throws IOException {
		this.port = port;
		serverSocket = new ServerSocket(port);
		hashtable = new Hashtable<String, TopicContext>();

	}

	@Override
	public void run() {
		try {
			while(true){
				//inicia uma nova conexão
				Socket connectionSocket = serverSocket.accept();
				Marshaller marshaller = new Marshaller();
				ServerMessageHandler smh = new ServerMessageHandler(connectionSocket);
				Message message = marshaller.unmarshall(smh.receive());
				
				System.out.println(message.getType());
				
				String topicName = message.getDestination();
				switch (message.getType()) {
					case "pub":
						
						//TopicService
						if(!hashtable.containsKey(topicName)){
							hashtable.put(topicName, new TopicContext(topicName));
						}						
						TopicContext topicContext = hashtable.get(topicName);
						topicContext.getMensagens().add(message);
						
						
						

						break;
					case "sub":				
						
						//TopicSender
						//tem que ser uma thread <- session
						topicContext = hashtable.get(topicName);
						String hostName = connectionSocket.getInetAddress().getHostName();
						topicContext.getSubscribers().add(new Subscriber(hostName, connectionSocket.getPort()));
					
						//Thread que vai comunicar para o cliente quando houver uma modificação no topico
						TopicSender topicSender = new TopicSender(topicContext);
						Thread consumerThread = new Thread(topicSender);
						consumerThread.start();
						
						break;					
					default:
						break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
