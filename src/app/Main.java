package app;

import jms.Context;
import jms.Message;
import jms.Topic;
import jms.TopicConnection;
import jms.TopicConnectionFactory;
import jms.TopicPublisher;
import jms.TopicSession;
import jms.TopicSubscriber;
import jms.TopicSubscriberListener;



public class Main {

	public static void main(String[] args)throws Exception{
		// get the initial context
		//I AM THE ADMIN
		Context ctx = new Context();
		ctx.bind("chat1", new Topic("chat1"));
		ctx.bind("connectionFactory", new TopicConnectionFactory("localhost", 8080));

		// lookup the topic object 
		//if there's no topic, create a new one
		Topic topic = (Topic)ctx.lookup("chat1");

		// lookup the topic connection factory
		TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.lookup("connectionFactory");

		// create a topic connection
		TopicConnection topicConn = connFactory.createTopicConnection();

		// create a topic session
//		TopicSession topicSession = topicConn.createTopicSession(false,
//				Session.AUTO_ACKNOWLEDGE);
		TopicSession topicSession = topicConn.createTopicSession();
		TopicSession topicSession1 = topicConn.createTopicSession();
		TopicSession topicSession2 = topicConn.createTopicSession();
		TopicSession topicSession3 = topicConn.createTopicSession();
		// create a topic publisher
		TopicPublisher topicPublisher = topicSession.createPublisher(topic);
		
		// create a topic subscriber
		TopicSubscriber topicSubscriber= topicSession1.createSubscriber(topic);
		
		// create a topic publisher
		TopicPublisher topicPublisher1 = topicSession2.createPublisher(topic);
				
		// create a topic subscriber
		TopicSubscriber topicSubscriber2= topicSession3.createSubscriber(topic);
				
		// create a simple message
		Message message = topicSession.createTextMessage("middleware é a disciplina mais legal");
		Message message2 = topicSession2.createTextMessage("vamos passar em middleware");
		topicPublisher.publish(message); 	
		TopicSubscriberListener listener = new TopicSubscriberListener(); 
		topicSubscriber.setMessageListener(listener,1);
		TopicSubscriberListener listener2 = new TopicSubscriberListener(); 
		topicSubscriber2.setMessageListener(listener2,2);
		topicPublisher1.publish(message2);
		topicConn.start();
		//espear um tempo para fechar
		 //topicConn.close();
		while(true);
	}
}
