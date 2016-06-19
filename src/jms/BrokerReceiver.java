package jms;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class BrokerReceiver implements Runnable{
	
	private ServerSocket serverSocket;
	private int port;
	
	private Hashtable<String, Object> hashtable;
	
	public BrokerReceiver(int port) throws IOException {
		this.port = port;
		serverSocket = new ServerSocket(port);
		hashtable = new Hashtable<String, Object>();

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
				
				switch (message.getType()) {
					case "pub":
						//TopicService		
						String topico = message.getDestination();
						hashtable.put(topico, message.getBody());					
						break;
					case "sub":				
						
						//TopicSender
						
						
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
