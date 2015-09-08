package soundsystem;

public class CDPlayer implements MediaPlayer {

	private CompactDisc cd;		
	
	private DVD dvd;
	
	
	
	public DVD getDvd() {
		return dvd;
	}

	public CompactDisc getCd() {
		return cd;
	}

	public void setCd(CompactDisc cd) {
		this.cd = cd;
	}
	
	public CDPlayer(CompactDisc cd, DVD dvd) {
		this.cd = cd;
		this.dvd = dvd;
	}
	
	public void play(){
		cd.playAlbum();
	}

	public void next() {
		cd.playSong();
		
	}
}
