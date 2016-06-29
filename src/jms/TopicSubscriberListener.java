package jms;

public class TopicSubscriberListener implements MessageListener{

	@Override
	public void onMessage(Message message,int id) {
		System.out.println("Subcriber com id"+ id+" recebeu a mensagem : " +message.getTextMessage());
	}

}
