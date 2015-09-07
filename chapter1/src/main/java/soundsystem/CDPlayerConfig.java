package soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CDConfig.class})
public class CDPlayerConfig {

	@Bean
	public MediaPlayer cdPlayer(CompactDisc cd) {
		return new CDPlayer(cd);
	}
	
}
