package jms;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientRequestHandler {

	private String host;
	private int port;
	private Socket clientSocket;
	private DataOutputStream outToServer;
	private DataInputStream inFromServer;

	public ClientRequestHandler(String host, int port) throws UnknownHostException, IOException {
		this.host = host;
		this.port = port;
		clientSocket = new Socket(this.host, this.port);
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		inFromServer = new DataInputStream(clientSocket.getInputStream());
	}

	void send(byte[] msg) throws UnknownHostException, IOException {
		int msgSize = msg.length;
		outToServer.writeInt(msgSize);
		outToServer.write(msg);
		outToServer.flush();

	}

	byte[] receive() throws IOException {
		byte[] msg;
		int msgSize = inFromServer.readInt();
		msg = new byte[msgSize];
		inFromServer.readFully(msg, 0, msgSize);

//		clientSocket.close();
//		outToServer.close();
//		inFromServer.close();
		return msg;

	}

}
