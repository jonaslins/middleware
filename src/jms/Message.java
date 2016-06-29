package jms;

public class Message implements MessageInterface {
	//head
	private String JMSDestination;
	private String JMSDeliveryMode; //specifies wheather the message is persistent
	private long JMSExpiration;// specifies the time when the message will expire
	private int JMSPriority; //Specifies the priority of the message within a 0 (low) to 9 (high) range.
	private int JMSMessageID; //Specifies a unique ID for the message within the context of a JMS provider installation
	private int JMSRedelivered;//Specifies whether the message has already been delivered but not acknowledged.
	private String JMSTimestamp;//Specifies the time when the JMS provider received the message.
	private int JMSCorrelationID; //A value that allows a client to define a correspondence between two messages.
	private String JMSReplyTo; //Specifies a destination where the consumer should send a reply.
	private int JMSType; //A value that can be evaluated by a message selector.
	//body
	private String TextMessage; //A message whose body contains a Java string, for example an XML message. 
	
	
	public Message() {
		super();
		
	}
	public Message(String jMSDestination, String jMSDeliveryMode, long jMSExpiration, int jMSPriority, int jMSMessageID,
			int jMSRedelivered, String jMSTimestamp, int jMSCorrelationID, String jMSReplyTo, int jMSType,
			String textMessage) {
		super();
		JMSDestination = jMSDestination;
		JMSDeliveryMode = jMSDeliveryMode;
		JMSExpiration = jMSExpiration;
		JMSPriority = jMSPriority;
		JMSMessageID = jMSMessageID;
		JMSRedelivered = jMSRedelivered;
		JMSTimestamp = jMSTimestamp;
		JMSCorrelationID = jMSCorrelationID;
		JMSReplyTo = jMSReplyTo;
		JMSType = jMSType;
		TextMessage = textMessage;
	}
	
	
	public String getJMSDestination() {
		return JMSDestination;
	}
	public void setJMSDestination(String jMSDestination) {
		JMSDestination = jMSDestination;
	}
	public String getJMSDeliveryMode() {
		return JMSDeliveryMode;
	}
	public void setJMSDeliveryMode(String jMSDeliveryMode) {
		JMSDeliveryMode = jMSDeliveryMode;
	}
	public long getJMSExpiration() {
		return JMSExpiration;
	}
	public void setJMSExpiration(long jMSExpiration) {
		JMSExpiration = jMSExpiration;
	}
	public int getJMSPriority() {
		return JMSPriority;
	}
	public void setJMSPriority(int jMSPriority) {
		JMSPriority = jMSPriority;
	}
	public int getJMSMessageID() {
		return JMSMessageID;
	}
	public void setJMSMessageID(int jMSMessageID) {
		JMSMessageID = jMSMessageID;
	}
	public int getJMSRedelivered() {
		return JMSRedelivered;
	}
	public void setJMSRedelivered(int jMSRedelivered) {
		JMSRedelivered = jMSRedelivered;
	}
	public String getJMSTimestamp() {
		return JMSTimestamp;
	}
	public void setJMSTimestamp(String jMSTimestamp) {
		JMSTimestamp = jMSTimestamp;
	}
	public int getJMSCorrelationID() {
		return JMSCorrelationID;
	}
	public void setJMSCorrelationID(int jMSCorrelationID) {
		JMSCorrelationID = jMSCorrelationID;
	}
	public String getJMSReplyTo() {
		return JMSReplyTo;
	}
	public void setJMSReplyTo(String jMSReplyTo) {
		JMSReplyTo = jMSReplyTo;
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
