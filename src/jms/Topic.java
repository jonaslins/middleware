package jms;

import java.util.Vector;

public class Topic {
	String name;
	Vector<Message> mensagens;
	
	public Topic(String topicName){
		this.name = topicName;
		this.mensagens = new Vector<Message>();
	}
	
	
	private void addMessage(Message msg){
		this.mensagens.add(msg);
	}
	
	private void removeMessage(Message msg){
		int index = this.mensagens.indexOf(msg);
		this.mensagens.remove(index);
	}


	private String getName() {
		return name;
	}


	private void setName(String name) {
		this.name = name;
	}


	private Vector<Message> getMensagens() {
		return mensagens;
	}


	private void setMensagens(Vector<Message> mensagens) {
		this.mensagens = mensagens;
	}
	
	
}
