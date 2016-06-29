package jms;

import java.io.IOException;

public class TopicConnectionFactory {
	private String ip;
	private int port;
	
	public TopicConnectionFactory(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	public TopicConnection createTopicConnection() throws IOException{
		return new TopicConnection(ip, port); 		
	}


}
