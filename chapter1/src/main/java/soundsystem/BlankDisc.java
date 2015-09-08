package soundsystem;

public class BlankDisc implements CompactDisc {

	private String album;
	private String artists;

	public BlankDisc(String album, String artists) {
		this.album = album;
		this.artists = artists;
	}

	public String getAlbum() {
		return album;
	}

	public String getArtists() {
		return artists;
	}

	public void playAlbum() {
		// TODO Auto-generated method stub

	}

	public void playSong() {
		// TODO Auto-generated method stub

	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setArtists(String artists) {
		this.artists = artists;
	}

}
