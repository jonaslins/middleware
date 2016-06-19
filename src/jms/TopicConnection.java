package jms;

import java.io.IOException;

public class TopicConnection {
	private String ip;
	private int port;
	private TopicSession topicSession;
	
	public TopicConnection(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public TopicSession createTopicSession() throws IOException {
		topicSession = new TopicSession(ip, port);
		return topicSession;
	}

	public void start() {
		Thread sessionThread = new Thread(topicSession);
		sessionThread.start();
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}
	
	

//
//	public BrokerReceiver(int port) throws IOException {
//		this.port = port;
//		serverSocket = new ServerSocket(port);
//	}
//
//	@Override
//	public void run() {
//		try {
//			while(true){
//				//inicia uma nova conexão
//				Socket connectionSocket = serverSocket.accept();
//				Marshaller marshaller = new Marshaller();
//				ServerMessageHandler smh = new ServerMessageHandler(connectionSocket);
//				Message message = marshaller.unmarshall(smh.receive());

}
