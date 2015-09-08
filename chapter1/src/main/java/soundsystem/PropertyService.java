package soundsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


public class PropertyService {

	@Autowired
	private Environment env;
	
	
	public String getRequiredProperty(String propertyKey) {
		return env.getRequiredProperty(propertyKey);
	}
	
	public String getProperty(String propertyKey) { 
		return env.getProperty(propertyKey);
	}
}
