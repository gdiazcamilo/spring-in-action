package soundsystem;

import org.springframework.stereotype.Component;

@Component
public class ElCuartetoDeNos implements CompactDisc {

	private String albumTitle = "Porfiado";
	private String artists = "El Cuarteto de Nos";
	
	public void play() {
		System.out.println(String.format("Playing %s by %s", albumTitle, artists));
	}

}
