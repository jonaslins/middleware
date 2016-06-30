package jms;

public class TopicSubscriberListener implements MessageListener{

	@Override
	public void onMessage(Message message,int id) {
		System.out.println("Subcriber recebeu a mensagem : " +message.getTextMessage()+ " do chat "+ id);
	}

}
