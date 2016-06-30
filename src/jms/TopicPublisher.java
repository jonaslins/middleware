package jms;

import java.io.IOException;
import java.net.Socket;

public class TopicPublisher {
	private Topic topicDestination; 
	Marshaller marshaller;
	MessageHandler smh;
	
	public TopicPublisher(Topic topicDestination, Socket socket) throws IOException {
		this.topicDestination = topicDestination;
		marshaller = new Marshaller();
		smh = new MessageHandler(socket);
	}

	public void publish(Message message) throws IOException {
		message.setJMSDestination(topicDestination.getName());
		smh.send(Marshaller.marshall(message));
	}
	
	public void ackpublish() throws IOException {
		InterMessage message = new InterMessage();
		message.setJMSDestination(topicDestination.getName());
		message.setJMSType(1); //1 - para publisher
		smh.send(Marshaller.marshall(message));
	}

}
