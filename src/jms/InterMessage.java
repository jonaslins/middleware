package jms;


public class InterMessage implements MessageInterface{
	//head
	private String JMSDestination;
	private int JMSType; //A value that can be evaluated by a message selector.
	//body
	private String TextMessage; //A message whose body contains a Java string, for example an XML message. 

	
	public InterMessage(){
	}
	
	public InterMessage(String destination) {
		this.JMSDestination = destination;
	}

	public String getJMSDestination() {
		return JMSDestination;
	}

	public void setJMSDestination(String jMSDestination) {
		JMSDestination = jMSDestination;
	}

	public int getJMSType() {
		return JMSType;
	}

	public void setJMSType(int jMSType) {
		JMSType = jMSType;
	}

	public String getTextMessage() {
		return TextMessage;
	}

	public void setTextMessage(String textMessage) {
		TextMessage = textMessage;
	}



	
}
