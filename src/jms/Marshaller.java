package jms;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.google.gson.Gson;


public class Marshaller {
	
	public static byte [] marshall (Message messageToBeMarshalled) throws IOException{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		Gson gson = new Gson();
		String json = gson.toJson(messageToBeMarshalled);
		byte[] message = json.getBytes();		
		byteStream.write(message);
		return message;
	}

	public static Message unmarshall (byte [] menssageToBeUnmarshalled) throws IOException, ClassNotFoundException{
		String json = new String(menssageToBeUnmarshalled);
		Gson gson = new Gson();
		return gson.fromJson(json, Message.class);  
	}
}
