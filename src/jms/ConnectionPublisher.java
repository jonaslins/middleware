package jms;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Hashtable;

public class ConnectionPublisher extends Thread{
	private Socket connectionSocket;
	private MessageHandler smh ;
	private TopicContext topicContext;

	public ConnectionPublisher(Socket connectionSocket, TopicContext topicContext) throws IOException{
		this.connectionSocket = connectionSocket;
		this.smh = new MessageHandler(this.connectionSocket);
		this.topicContext = topicContext;
	}


	@Override
	public void run() {
		while(true){
			Message message = null;
			try {
				message = (Message) Marshaller.unmarshall(smh.receive());
			} catch (SocketException e) { // significa que a conexão não existe mais
				// TODO: handle exception
			}catch (ClassNotFoundException e) {
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




