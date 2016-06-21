package jms;

import java.io.IOException;
import java.net.Socket;

public class TopicSubscriber {
	private Topic topicDestination; 
	private Socket socket;
	Marshaller marshaller;
	ServerMessageHandler smh;
		
	
	public TopicSubscriber(Topic topicDestination, Socket socket) throws IOException {
		this.topicDestination = topicDestination;
		this.socket = socket;
		this.marshaller = new Marshaller();
		this.smh = new ServerMessageHandler(socket);
	}
	
	
	public Topic getTopicDestination() {
		return topicDestination;
	}
	public void setTopicDestination(Topic topicDestination) {
		this.topicDestination = topicDestination;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public Marshaller getMarshaller() {
		return marshaller;
	}
	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}
	public ServerMessageHandler getSmh() {
		return smh;
	}
	public void setSmh(ServerMessageHandler smh) {
		this.smh = smh;
	}
	
	public void subscribe(Message message) throws IOException {
		message.setDestination(topicDestination.getName());
		message.setType("sub");
		smh.send(marshaller.marshall(message));
	}
	
	public void recive() throws IOException, ClassNotFoundException {
		while(true){
			byte[] m = smh.receive();
			Message msg = marshaller.unmarshall(m);
			System.out.println("Subcriber recebeu a mensagem : " +msg.getBody());
		}
	}
	
	
}
