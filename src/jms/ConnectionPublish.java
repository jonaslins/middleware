package jms;

import java.io.IOException;
import java.net.Socket;
import java.util.Hashtable;

public class ConnectionPublish extends Thread{
	private Socket connectionSocket;
	private ServerMessageHandler smh ;
	private TopicContext topicContext;

	public ConnectionPublish(Socket connectionSocket, TopicContext topicContext) throws IOException{
		this.connectionSocket = connectionSocket;
		this.smh = new ServerMessageHandler(this.connectionSocket);
		this.topicContext = topicContext;
	}


	@Override
	public void run() {
		while(true){
			Message message = null;
			try {
				message = (Message) Marshaller.unmarshall(smh.receive());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("o broker recebeu " +message.getTextMessage());
			topicContext.getMensagens().add((Message) message);
		}

	}
}




