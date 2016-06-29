package jms;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerMessageHandler{
	private DataOutputStream outToClient;
	private DataInputStream inFromClient;
	
	public ServerMessageHandler(Socket connectionSocket) throws IOException {
		outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		inFromClient = new DataInputStream(connectionSocket.getInputStream());
	}

	public void send(byte[] msg) throws IOException{
		int sentMessageSize = msg.length;
		outToClient.writeInt(sentMessageSize);
		outToClient.write(msg);
		outToClient.flush();		
	}
	
	public byte[] receive() throws IOException{

		int receiveMessageSize = inFromClient.readInt();
		byte[] receivedMsg = new byte[receiveMessageSize];
	
		inFromClient.read(receivedMsg, 0, receiveMessageSize);		
		
		return receivedMsg;
	}


}
