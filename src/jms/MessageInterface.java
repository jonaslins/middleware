package jms;

import java.io.Serializable;

public interface MessageInterface extends Serializable{
	public void setJMSDestination(String destination) ;
	public String getJMSDestination() ;
	public void setJMSType( int JMSType);
	public int getJMSType();
}
