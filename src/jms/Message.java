package jms;

import java.util.List;

public class Message {
	//Header
	private String JMSDestination;
	private String JMSDeliveryMode;
	private String JMSExpiration;
	private String JMSPriority;
	private String JMSMessageID;
	private String JMSRedelivery;
	private String JMSTimestamp;
	private String JMSCorrelationId;
	private String JMSReplyTo;
	private String JMSType;
	
	//Properties
	private String JMSXUserID;
	private String JMSXAppID;
	private String JMSXDeliveryCount;
	private String JMSXGroupID;
	private String JMSXGroupSeq;
	private String JMSXProducerTXID;
	private String JMSXConsumerTXID;
	private String JMSXRCVTimestamp;
	private String JMSXState;
	
	//Body
	private String textMessage;

	public Message(String jMSDestination, String jMSDeliveryMode,
			String jMSExpiration, String jMSPriority, String jMSMessageID,
			String jMSRedelivery, String jMSTimestamp, String jMSCorrelationId,
			String jMSReplyTo, String jMSType, String jMSXUserID,
			String jMSXAppID, String jMSXDeliveryCount, String jMSXGroupID,
			String jMSXGroupSeq, String jMSXProducerTXID,
			String jMSXConsumerTXID, String jMSXRCVTimestamp, String jMSXState,
			String textMessage) {
		JMSDestination = jMSDestination;
		JMSDeliveryMode = jMSDeliveryMode;
		JMSExpiration = jMSExpiration;
		JMSPriority = jMSPriority;
		JMSMessageID = jMSMessageID;
		JMSRedelivery = jMSRedelivery;
		JMSTimestamp = jMSTimestamp;
		JMSCorrelationId = jMSCorrelationId;
		JMSReplyTo = jMSReplyTo;
		JMSType = jMSType;
		JMSXUserID = jMSXUserID;
		JMSXAppID = jMSXAppID;
		JMSXDeliveryCount = jMSXDeliveryCount;
		JMSXGroupID = jMSXGroupID;
		JMSXGroupSeq = jMSXGroupSeq;
		JMSXProducerTXID = jMSXProducerTXID;
		JMSXConsumerTXID = jMSXConsumerTXID;
		JMSXRCVTimestamp = jMSXRCVTimestamp;
		JMSXState = jMSXState;
		this.textMessage = textMessage;
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

	public String getJMSExpiration() {
		return JMSExpiration;
	}

	public void setJMSExpiration(String jMSExpiration) {
		JMSExpiration = jMSExpiration;
	}

	public String getJMSPriority() {
		return JMSPriority;
	}

	public void setJMSPriority(String jMSPriority) {
		JMSPriority = jMSPriority;
	}

	public String getJMSMessageID() {
		return JMSMessageID;
	}

	public void setJMSMessageID(String jMSMessageID) {
		JMSMessageID = jMSMessageID;
	}

	public String getJMSRedelivery() {
		return JMSRedelivery;
	}

	public void setJMSRedelivery(String jMSRedelivery) {
		JMSRedelivery = jMSRedelivery;
	}

	public String getJMSTimestamp() {
		return JMSTimestamp;
	}

	public void setJMSTimestamp(String jMSTimestamp) {
		JMSTimestamp = jMSTimestamp;
	}

	public String getJMSCorrelationId() {
		return JMSCorrelationId;
	}

	public void setJMSCorrelationId(String jMSCorrelationId) {
		JMSCorrelationId = jMSCorrelationId;
	}

	public String getJMSReplyTo() {
		return JMSReplyTo;
	}

	public void setJMSReplyTo(String jMSReplyTo) {
		JMSReplyTo = jMSReplyTo;
	}

	public String getJMSType() {
		return JMSType;
	}

	public void setJMSType(String jMSType) {
		JMSType = jMSType;
	}

	public String getJMSXUserID() {
		return JMSXUserID;
	}

	public void setJMSXUserID(String jMSXUserID) {
		JMSXUserID = jMSXUserID;
	}

	public String getJMSXAppID() {
		return JMSXAppID;
	}

	public void setJMSXAppID(String jMSXAppID) {
		JMSXAppID = jMSXAppID;
	}

	public String getJMSXDeliveryCount() {
		return JMSXDeliveryCount;
	}

	public void setJMSXDeliveryCount(String jMSXDeliveryCount) {
		JMSXDeliveryCount = jMSXDeliveryCount;
	}

	public String getJMSXGroupID() {
		return JMSXGroupID;
	}

	public void setJMSXGroupID(String jMSXGroupID) {
		JMSXGroupID = jMSXGroupID;
	}

	public String getJMSXGroupSeq() {
		return JMSXGroupSeq;
	}

	public void setJMSXGroupSeq(String jMSXGroupSeq) {
		JMSXGroupSeq = jMSXGroupSeq;
	}

	public String getJMSXProducerTXID() {
		return JMSXProducerTXID;
	}

	public void setJMSXProducerTXID(String jMSXProducerTXID) {
		JMSXProducerTXID = jMSXProducerTXID;
	}

	public String getJMSXConsumerTXID() {
		return JMSXConsumerTXID;
	}

	public void setJMSXConsumerTXID(String jMSXConsumerTXID) {
		JMSXConsumerTXID = jMSXConsumerTXID;
	}

	public String getJMSXRCVTimestamp() {
		return JMSXRCVTimestamp;
	}

	public void setJMSXRCVTimestamp(String jMSXRCVTimestamp) {
		JMSXRCVTimestamp = jMSXRCVTimestamp;
	}

	public String getJMSXState() {
		return JMSXState;
	}

	public void setJMSXState(String jMSXState) {
		JMSXState = jMSXState;
	}

	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	
	
	

}
