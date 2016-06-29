package jms;

import java.io.IOException;
import java.net.Socket;


public class TopicSession {
	private TopicSubscriber topicSubscriber;
	private TopicPublisher topicPublisher;
	private Socket socket;

	public TopicSession(String ip, int port) throws IOException {
		this.socket = new Socket(ip, port);
	}

	public TopicPublisher createPublisher(Topic topic) throws IOException {
		this.topicPublisher =  new TopicPublisher(topic, socket);
		this.topicPublisher.ackpublish();
		 return topicPublisher;
	}

	public TopicSubscriber createSubscriber(Topic topic) throws IOException {
		 this.topicSubscriber = new TopicSubscriber(topic, socket);
		 topicSubscriber.subscribe();
		 return topicSubscriber;
	}

	public Message createTextMessage(String textMessage) {
		Message message = new Message();
		message.setTextMessage(textMessage);
		return message;
	}

	public TopicSubscriber getTopicSubscriber() {
		return topicSubscriber;
	}

	public void setTopicSubscriber(TopicSubscriber topicSubscriber) {
		this.topicSubscriber = topicSubscriber;
	}

	public TopicPublisher getTopicPublisher() {
		return topicPublisher;
	}

	public void setTopicPublisher(TopicPublisher topicPublisher) {
		this.topicPublisher = topicPublisher;
	}

	
	
}
