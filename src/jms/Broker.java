package jms;

import java.io.IOException;

public class Broker {

	public static void main(String[] args) throws Exception {
		
		BrokerReceiver receiver = new BrokerReceiver(8080);
		Thread threadReceiver = new Thread(receiver);
		threadReceiver.start();
//		BrokerSender sender = new BrokerSender();
//		Thread threadSender = new Thread(sender);

	}

}
