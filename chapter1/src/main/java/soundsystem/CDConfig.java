package soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CDConfig {

	@Bean
	@Scope("singleton")
	public CompactDisc elCuartetoDeNos() {
		return new ElCuartetoDeNos();
	}
}
