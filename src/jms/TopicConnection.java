package jms;

import java.io.IOException;
import java.util.Vector;

public class TopicConnection {
	private String ip;
	private int port;
	private Vector <TopicSession> topicSessions;
	
	public TopicConnection(String ip, int port) {
		this.ip = ip;
		this.port = port;
		this.topicSessions = new Vector<TopicSession>();
	}

	public TopicSession createTopicSession() throws IOException {
		TopicSession topicSession = new TopicSession(ip, port);
		this.topicSessions.add(topicSession);
		return topicSession;
	}

	public void start() {
		for(int i =0;i<topicSessions.size();i++){
			if(topicSessions.get(i).getTopicSubscriber()!=null){
				topicSessions.get(i).getTopicSubscriber().start();
			}
				
		}
	}

	public void close() {
		for(int i =0;i<topicSessions.size();i++){
			if(topicSessions.get(i).getTopicSubscriber()!=null){
				topicSessions.get(i).getTopicSubscriber().setCancel(true);
			}	
		}
	}

}
