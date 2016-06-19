package jms;



public class Main {

	public static void main(String[] args)throws Exception{
		// get the initial context
		//I AM THE ADMIN
		Context ctx = new Context();
		ctx.bind("chat1", new Topic("chat1"));
		ctx.bind("connectionFactory", new TopicConnectionFactory("localhost", 8080));
//     + broker <-TopicConnectionFactory
//		- topic(chat1) <- Topic
//		- topic(chat2)
//		- topic(chat3)
//		

		// lookup the topic object 
		//if there's no topic, create a new one
		Topic topic = (Topic)ctx.lookup("chat1");

		// lookup the topic connection factory
		TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx
				.lookup("connectionFactory");

		// create a topic connection
		TopicConnection topicConn = connFactory.createTopicConnection();

		// create a topic session
//		TopicSession topicSession = topicConn.createTopicSession(false,
//				Session.AUTO_ACKNOWLEDGE);
		TopicSession topicSession = topicConn.createTopicSession();

		// create a topic publisher
		TopicPublisher topicPublisher = topicSession.createPublisher(topic);

		// create a simple message
		Message message = topicSession.createTextMessage("middleware é a disciplina mais legal");

		System.out.println("created durable subscriber mySub");
		
//		topicConn.start(); 
		topicPublisher.publish(message); 		
		// close the topic connection
		topicConn.close();		
		
	}
}
