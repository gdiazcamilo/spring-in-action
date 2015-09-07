package soundsystem;

public class CDPlayer implements MediaPlayer {

	private CompactDisc cd;		
	
	public CompactDisc getCd() {
		return cd;
	}

	public void setCd(CompactDisc cd) {
		this.cd = cd;
	}
	
	public CDPlayer(CompactDisc cd) {
		this.cd = cd;
	}
	
	public void play(){
		cd.playAlbum();
	}

	public void next() {
		cd.playSong();
		
	}
}
