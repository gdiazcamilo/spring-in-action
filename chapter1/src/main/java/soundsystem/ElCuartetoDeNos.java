package soundsystem;

public class ElCuartetoDeNos implements CompactDisc {

	private String albumTitle = "Porfiado";
	private String artists = "El Cuarteto de Nos";

	public void playAlbum() {
		System.out.println(String.format("Playing %s by %s", albumTitle, artists));
	}

	public void playSong() {
		System.out.println(String.format("Playing  %s by %s", "'Nada mejor que hacer'", artists));
	}

	public void playTrack(int trackNumber) {
		// TODO Auto-generated method stub
		
	}
	
}
