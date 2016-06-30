package app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Observable;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import jms.Context;
import jms.Message;
import jms.MessageListener;
import jms.Topic;
import jms.TopicConnection;
import jms.TopicConnectionFactory;
import jms.TopicPublisher;
import jms.TopicSession;
import jms.TopicSubscriber;

public class ChatGUI extends JFrame implements MessageListener {

	private JTextArea textArea;
	private JTextField inputTextField;
	private JButton sendButton;
	
	
	private TopicPublisher topicPublisher;
	private TopicSession topicPublisherSession;


	public ChatGUI(String title) throws Exception {
		super(title);
		buildGUI();
		connectChat();
	}

	public static void main(String[] args) throws Exception {

		JFrame frame = new ChatGUI("Client chat");
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

	}

	public void connectChat() throws Exception{
		String chatName = "musica";
		
		Context ctx = new Context();
		ctx.bind(chatName, new Topic(chatName));
		ctx.bind("connectionFactory", new TopicConnectionFactory("localhost", 8080));

		// lookup the topic object 
		//if there's no topic, create a new one
		Topic topic = (Topic)ctx.lookup(chatName);

		// lookup the topic connection factory
		TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.lookup("connectionFactory");

		// create a topic connection
		TopicConnection topicConn = connFactory.createTopicConnection();

		// create a topic session
		//				TopicSession topicSession = topicConn.createTopicSession(false,
		//						Session.AUTO_ACKNOWLEDGE);
		topicPublisherSession = topicConn.createTopicSession();
		TopicSession topicSubscriberSession = topicConn.createTopicSession();

		// create a topic subscriber
		TopicSubscriber topicSubscriber = topicSubscriberSession.createSubscriber(topic);


		// create a topic publisher
		topicPublisher = topicPublisherSession.createPublisher(topic);
		
		topicSubscriber.setMessageListener(this);
		topicConn.start();
		
	}


	private void buildGUI() {
		textArea = new JTextArea(20, 50);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		add(new JScrollPane(textArea), BorderLayout.CENTER);

		Box box = Box.createHorizontalBox();
		add(box, BorderLayout.SOUTH);
		inputTextField = new JTextField();
		sendButton = new JButton("Send");
		box.add(inputTextField);
		box.add(sendButton);

		// Action for the inputTextField and the goButton
		ActionListener sendListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String str = inputTextField.getText();
				if (str != null && str.trim().length() > 0)
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							Message message = topicPublisherSession.createTextMessage(str);

//							textArea.append(str);
//							textArea.append("\n");
							try {
								topicPublisher.publish(message);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				// chatAccess.send(str);
				inputTextField.selectAll();
				inputTextField.requestFocus();
				inputTextField.setText("");
			}
		};
		inputTextField.addActionListener(sendListener);
		sendButton.addActionListener(sendListener);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// chatAccess.close();
			}
		});
	}

	@Override
	public void onMessage(final Message message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textArea.append(message.getTextMessage());
				textArea.append("\n");

			}
		});
		
	}
}
