package jms;

import java.io.IOException;

public class Broker {

	public static void main(String[] args) throws Exception {

		ServerRequestHandler srh = new ServerRequestHandler(8080);
		srh.waitConnection();

		Marshaller marshaller = new Marshaller();

		while (true) {
			byte[] msgToBeUnmashalled = srh.receive();
			Message msgUnmashalled = marshaller.unmarshall(msgToBeUnmashalled);
			msgUnmashalled.getJMSDestination(); //chat1
			
		}

	}

}
