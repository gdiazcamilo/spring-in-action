package soundsystem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class TrackerCounterConfig {

	@Bean
	public CompactDisc elCuartetoDeNos() {
		BlankDisc cd = new BlankDisc("Porfiado", "El cuarteto de nos");
		List<String> tracks = new ArrayList<String>();
		tracks.add("Lo malo de ser bueno");
		tracks.add("El balcón de Paul");
		tracks.add("El lado soleado de la calle");
		tracks.add("No te invité a mi cumpleaños");
		tracks.add("Insaciable");
		
		cd.setTracks(tracks);
		
		return cd;
	}
	
	@Bean
	public TrackCounter trackCounter(){
		return new TrackCounter();
	}
	
}
