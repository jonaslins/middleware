package jms;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TopicPublisher {
	private Topic topicDestination; 
	private Socket socket;
	Marshaller marshaller;
	ServerMessageHandler smh;
	
	public TopicPublisher(Topic topicDestination, Socket socket) throws IOException {
		this.topicDestination = topicDestination;
		marshaller = new Marshaller();
		smh = new ServerMessageHandler(socket);
	}

	public void publish(Message message) throws IOException {
		message.setDestination(topicDestination.getName());
		message.setType("pub");
		smh.send(marshaller.marshall(message));
	}

}
