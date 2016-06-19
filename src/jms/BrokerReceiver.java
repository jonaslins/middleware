package jms;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class BrokerReceiver implements Runnable{
	
	private ServerSocket serverSocket;
	private int port;

	public BrokerReceiver(int port) throws IOException {
		this.port = port;
		serverSocket = new ServerSocket(port);
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
				
				switch (message.getType()) {
				case "pub":
					//TopicService		
					Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
					String topico = message.getDestination();
					hashtable.put(topico, message.getBody());					
					break;
				case "sub":				
					
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
