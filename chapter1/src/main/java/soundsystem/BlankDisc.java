package soundsystem;

import java.util.List;


public class BlankDisc implements CompactDisc {

	private String album;
	private String artists;
	private List<String> tracks;

	public BlankDisc(String album, String artists) {
		this.album = album;
		this.artists = artists;
	}
	
	public void playTrack(int trackNumber) {
		System.out.println(tracks.get(trackNumber));
	}
	
	public void playAlbum() {
		// TODO Auto-generated method stub

	}

	public void playSong() {
		// TODO Auto-generated method stub

	}
	public List<String> getTracks() {
		return tracks;
	}
	
	public void setTracks(List<String> tracks) {
		this.tracks = tracks;
	}

	public String getAlbum() {
		return album;
	}

	public String getArtists() {
		return artists;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setArtists(String artists) {
		this.artists = artists;
	}

}
