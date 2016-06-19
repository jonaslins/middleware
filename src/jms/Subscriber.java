package jms;

import java.net.Socket;

public class Subscriber {
	private int port;
	private String ip;
	private Socket socket;
	public Subscriber(String ip, int port,Socket socket) {
		super();
		this.port = port;
		this.ip = ip;
		this.socket = socket;
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
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	
}
