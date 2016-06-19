package jms;

import java.util.List;

public class Message {

	private String destination; //topico
	private String type; //sub ou pub
	private String body; //conteúdo da mensagem
	private long timestamp; //hora que chegou no broker
	
	public Message(){
	}
	
	public Message(String destination) {
		super();
		this.destination = destination;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
