package jms;

public interface MessageInterface {
	public void setJMSDestination(String destination) ;
	public String getJMSDestination() ;
	public void setJMSType( int JMSType);
	public int getJMSType();
}
