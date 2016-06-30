package jms;

public class TopicSubscriberListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		System.out.println("Subcriber recebeu a mensagem : " +message.getTextMessage());
	}

}
