package jms;

import java.io.IOException;
import java.net.Socket;

public class TopicSubscriber extends Thread{
	private Topic topicDestination; 
	private Socket socket;
	Marshaller marshaller;
	MessageHandler smh;
	private MessageListener messageListener;
	boolean cancel = false;
	
	public TopicSubscriber(Topic topicDestination, Socket socket) throws IOException {
		this.topicDestination = topicDestination;
		this.socket = socket;
		this.marshaller = new Marshaller();
		this.smh = new MessageHandler(socket);
		this.cancel = false;
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
	public MessageHandler getSmh() {
		return smh;
	}
	public void setSmh(MessageHandler smh) {
		this.smh = smh;
	}
	
	public void subscribe() throws IOException {
		InterMessage message = new InterMessage();
		message.setJMSDestination(topicDestination.getName());
		message.setJMSType(2);//2 para subscriber
		smh.send(marshaller.marshall(message));
	}
	
	public Message recive() throws IOException, ClassNotFoundException {
		byte[] m = smh.receive();
		Message msg = (Message) marshaller.unmarshall(m);
		return msg;
	}
	
	public void setMessageListener(MessageListener listener) throws IOException, ClassNotFoundException {
			this.messageListener = listener;	
	}
	
	public void run(){
		while(!cancel){
			try {
				Message msg = recive();
				if(!cancel)
					this.messageListener.onMessage(msg);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setCancel(boolean cancel){
		this.cancel = cancel;
	}
	
}