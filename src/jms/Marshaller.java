package jms;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Marshaller {
	
	public static byte [] marshall (MessageInterface messageToBeMarshalled) throws IOException{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
		objectStream.writeObject(messageToBeMarshalled);
		
		return byteStream.toByteArray();
	}

	public static MessageInterface unmarshall (byte [] menssageToBeUnmarshalled) throws IOException, ClassNotFoundException{
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(menssageToBeUnmarshalled);
		ObjectInputStream objectStream = new ObjectInputStream(byteStream);
		
		return (MessageInterface) objectStream.readObject();  
	}
}
