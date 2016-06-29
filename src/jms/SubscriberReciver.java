package jms;

import java.io.IOException;

public class SubscriberReciver implements Runnable {
	Marshaller marshaller;
	ServerMessageHandler smh;
	
	public SubscriberReciver(Marshaller marshaller, ServerMessageHandler smh) {
		this.marshaller = marshaller;
		this.smh = smh;
	}



	@Override
	public void run() {
		while(true){
			byte[] m = null;
			try {
				m = smh.receive();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Message msg = null;
			try {
				msg = (Message) Marshaller.unmarshall(m);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Subcriber recebeu a mensagem : " +msg.getTextMessage());
		}
		
	}

}
