package soundsystem;

public class KennyG implements CompactDisc {

	private String albumTitle = "Love ballads";
	private String artists = "Kenny G";
	
	public void playAlbum() {
		System.out.println(String.format("Playing %s by %s", albumTitle, artists));
	}

	public void playSong() {
		System.out.println(String.format("Playing %s by %s", "'Loving you'", artists));
		
	}

	public void playTrack(int trackNumber) {
		// TODO Auto-generated method stub
		
	}

}
