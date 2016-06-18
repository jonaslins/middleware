package jms;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRequestHandler {
	
	private int portNumber;
	private ServerSocket welcomeSocket;
	private Socket connectionSocket;
	
	private int sentMessageSize;
	private int receiveMessageSize;
	private DataOutputStream outToClient;
	private DataInputStream inFromClient;
	boolean first = true;
	public ServerRequestHandler(int port) throws IOException {
		this.portNumber = port;
		welcomeSocket = new ServerSocket(portNumber);
	}
	
	public void waitConnection() throws IOException{
		connectionSocket = welcomeSocket.accept();
		
		outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		inFromClient = new DataInputStream(connectionSocket.getInputStream());
	}	
	
	public void send(byte[] msg) throws IOException{
		
		sentMessageSize = msg.length;
		outToClient.writeInt(sentMessageSize);
		outToClient.write(msg);
		outToClient.flush();		
	}
	
	public byte[] receive() throws IOException{

		receiveMessageSize = inFromClient.readInt();
		byte[] receivedMsg = new byte[receiveMessageSize];
	
		inFromClient.read(receivedMsg, 0, receiveMessageSize);		
		
		return receivedMsg;
	}
	
	public void closeConnections() throws IOException{
		outToClient.close();
		inFromClient.close();
		connectionSocket.close();
		welcomeSocket.close();
	}
	

}
