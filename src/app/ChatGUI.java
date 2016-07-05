package app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	
	// componentes da GUI
	private JTextArea textArea;
	private JTextField inputTextField;
	private JButton attButton;
	private JButton createButton;
	private JButton sendButton;
	private DefaultListModel listModel;
	private ListSelectionModel selectionModel;
	private String userName;
	
	// pub/sub sessions
	private TopicPublisher topicPublisher;
	private TopicSession topicPublisherSession;
	TopicConnection topicConn;

	
	private Context context;

	public ChatGUI(Context context) throws Exception {
		//super(title);
		this.context = context;
		userName();
		buildGUI();
		listAvaiableChats();
//		connectChat();
	}



	public static void main(String[] args) throws Exception {
				
		Context ctx = new Context();
		ctx.bind("connectionFactory", new TopicConnectionFactory("localhost", 8080));
		
		JFrame frame = new ChatGUI(ctx);
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	

	}

	public JList buildChatList(){		
		JList list = new JList(listModel); //data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setSize(50,50);
		
		selectionModel = list.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(selectionModel.getValueIsAdjusting()){
					int i = selectionModel.getMinSelectionIndex();
					String chatName = (String) listModel.getElementAt(i);
					System.out.println(chatName);
					try {
						connectChat(chatName);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		return list;
	}
	
	private void listAvaiableChats() {
		List<String> list = getContext().list();
		for (String string : list) {
			if(!listModel.contains(string))
				listModel.addElement(string);			
		}
	}

	public void connectChat(String chatName) throws Exception{
		
		if(topicConn!=null){
			topicConn.close();
		}

		Context ctx = getContext();
		ctx.bind(chatName, new Topic(chatName));
		
		// lookup the topic object 
		//if there's no topic, create a new one
		Topic topic = (Topic)ctx.lookup(chatName);

		// lookup the topic connection factory
		TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.lookup("connectionFactory");

		// create a topic connection
		topicConn = connFactory.createTopicConnection();

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
		
		textArea.append("Bem vindo ao chat "+ chatName.toUpperCase());
		textArea.append("\n");
		
	}


	private void buildGUI() {
		this.setTitle("Chat usuário: "+userName);
		listModel = new DefaultListModel();
		textArea = new JTextArea(20, 50);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		add(buildChatList(), BorderLayout.WEST);
		
		Box box = Box.createHorizontalBox();
		add(box, BorderLayout.SOUTH);
		inputTextField = new JTextField();
		sendButton = new JButton("Enviar");
		createButton = new JButton("Criar chat");
		attButton = new JButton("Atualizar");
		box.add(attButton);
		box.add(createButton);
		box.add(inputTextField);
		box.add(sendButton);

		// Action for the inputTextField and the goButton
		ActionListener sendListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String str = inputTextField.getText();
				if (str != null && str.trim().length() > 0)
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							if(topicPublisherSession!=null){
								Message message = topicPublisherSession.createObjectMessage(new AppMessage(userName, str));
	
	//							textArea.append(str);
	//							textArea.append("\n");
								try {
									topicPublisher.publish(message);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
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
		
		
		ActionListener createBtnListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = (String)JOptionPane.showInputDialog(
	                    getParent(),
	                    "Digite o nome do chat:",
	                    "Criar nova sala de chat",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    null,
	                    null);

				//If a string was returned, say so.
				if ((s != null) && (s.length() > 0)) {
					listModel.addElement(s);
					selectionModel.setSelectionInterval(0, listModel.getSize()-1);
					try {
						connectChat(s);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				}
			}
		};		
		createButton.addActionListener(createBtnListener);
		
		
		ActionListener attListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listAvaiableChats();
			}
		};
		attButton.addActionListener(attListener);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// chatAccess.close();
			}
		});
	}

	public void userName(){
		String s = (String)JOptionPane.showInputDialog(
                getParent(),
                "Digite o nome do usuário:",
                "Nome usuário",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);

		//If a string was returned, say so.
		if ((s != null) && (s.length() > 0)) {
			this.userName = s;
		}			
	}
	@Override
	public void onMessage(final Message message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AppMessage appmesg = (AppMessage) message.getObjectMessage();
				String userName = appmesg.getUserName();
				String mensagem = appmesg.getMessage();
				textArea.append(userName+" > " + mensagem);
				textArea.append("\n");
			}
		});
		
	}
	
	public Context getContext() {
		return context;
	}
	
}
