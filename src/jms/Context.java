package jms;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

public class Context {

	// Tupla <serviceNamee,Object>
	private Map<String, Object> destinationMap;

	public Context() {
		this.destinationMap = new HashMap<String,Object>();
	}

	public Object lookup(String name) {
		return destinationMap.get(name);
	}

	public void bind(String serviceName ,Object object) {
		if(!destinationMap.containsKey(serviceName)){
			destinationMap.put(serviceName, object);	
		}	
	}
	
	public List<String> list() {
		try{
			List<String> topicNames =new ArrayList<String>();
			Socket socket = new Socket("localhost", 8080);
			MessageHandler smh = new MessageHandler(socket);
			InterMessage mesg = new InterMessage();
			mesg.setJMSType(3);//quer lista de topicos
			smh.send(Marshaller.marshall(mesg));
			InterMessage interMessage = (InterMessage) Marshaller.interUnmarshall(smh.receive());
			String names = interMessage.getTextMessage();
			String [] array = names.split(",");
			for(int i =0;i<array.length;i++){
				if ((array[i] != null) && (array[i].length() > 0))
					topicNames.add(array[i]);
			}
			return topicNames;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}