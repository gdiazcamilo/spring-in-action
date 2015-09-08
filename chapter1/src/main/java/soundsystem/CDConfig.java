package soundsystem;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource("classpath:soundsystem/app.properties")
public class CDConfig {

	@Bean
	@Primary
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public CompactDisc elCuartetoDeNos() {
		return new ElCuartetoDeNos();
	}
	
	@Bean
	public CompactDisc kennyG() {
		return new KennyG();
	}
	
	@Bean
	@Qualifier("dvd")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public DVD dvd() {
		return new DVD();
	}
	
	@Bean
	public PropertyService propertyService() {
		return new PropertyService();
	}
}
