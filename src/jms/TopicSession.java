package jms;

import java.io.IOException;
import java.net.Socket;

public class TopicSession implements Runnable{
	
	private Socket socket;

	public TopicSession(String ip, int port) throws IOException {
		this.socket = new Socket(ip, port);
	}

	public TopicPublisher createPublisher(Topic topic) throws IOException {
		return new TopicPublisher(topic, socket);
	}

	@Override
	public void run() {
	}

	public Message createTextMessage(String textMessage) {
		Message message = new Message();
		message.setBody(textMessage);
		return message;
	}

}
