package soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CDPlayerConfig {

	@Bean
	@Scope("singleton")
	public CompactDisc elCuartetoDeNos() {
		return new ElCuartetoDeNos();
	}
	
	@Bean
	public MediaPlayer cdPlayer(CompactDisc cd) {
		return new CDPlayer(cd);
	}
	
}
