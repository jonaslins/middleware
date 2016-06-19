package jms;

import java.util.Vector;

public class TopicContext {
	String name;
	Vector<Message> mensagens;
	Vector<Subscriber> subscribers;
	public TopicContext(String topicName){
		this.name = topicName;
		this.mensagens = new Vector<Message>();
		this.subscribers = new Vector<Subscriber>();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Vector<Message> getMensagens() {
		return mensagens;
	}


	public void setMensagens(Vector<Message> mensagens) {
		this.mensagens = mensagens;
	}


	public Vector<Subscriber> getSubscribers() {
		return subscribers;
	}


	public void setSubscribers(Vector<Subscriber> subscribers) {
		this.subscribers = subscribers;
	}
	
	
}
