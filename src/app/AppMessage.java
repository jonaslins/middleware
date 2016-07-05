package app;

import java.io.Serializable;

import jms.Message;

public class AppMessage implements Serializable{
	private String userName;
	private String message;
	
	public AppMessage(String userName, String message) {
		this.userName = userName;
		this.message = message;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
