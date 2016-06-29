package jms;

public interface MessageListener {
	
	public void onMessage(Message message,int id);
}
