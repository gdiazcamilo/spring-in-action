package soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class CDConfig {

	@Bean
	@Primary
	@Scope("singleton")
	public CompactDisc elCuartetoDeNos() {
		return new ElCuartetoDeNos();
	}
	
	@Bean
	public CompactDisc kennyG() {
		return new KennyG();
	}
}
