package jms;

public class Subscriber {
	private int port;
	private String ip;
	
	public Subscriber(int port, String ip) {
		super();
		this.port = port;
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
