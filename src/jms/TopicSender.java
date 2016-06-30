package jms;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class TopicSender implements Runnable{

	TopicContext topicContext;
	public TopicSender(TopicContext topicContext) {
		this.topicContext = topicContext;
	}

	@Override
	public void run() {
		while(true){
			
			try {	//espera um pouco alguem dar um subscribe
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			for(int i =0;i<topicContext.mensagens.size();i++){
				System.out.println("o total de mensagens nesse topico eh "+topicContext.mensagens.size());
				for(int j =0;j<topicContext.subscribers.size();j++){
					try {
						byte[] message = Marshaller.marshall(topicContext.mensagens.get(i));
						Socket sckt = topicContext.subscribers.get(j).getSocket();
						ServerMessageHandler smh = new ServerMessageHandler(sckt);
						smh.send(message);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			topicContext.mensagens = new Vector<Message>();
			
		}
		
	}

}
