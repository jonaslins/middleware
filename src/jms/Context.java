package jms;

import java.util.HashMap;
import java.util.Map;

public class Context {

	// Tupla <serviceNamee,Object>
	private Map<String,Object> destinationMap;

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

}