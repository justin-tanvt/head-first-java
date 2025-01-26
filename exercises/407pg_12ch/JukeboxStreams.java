import java.util.List;
import java.util.stream.Collectors;

public class JukeboxStreams {
	public static void main(String[] args) {
		List<Song> songs = new Songs().getSongs();

		String songTitle = "With a Little Help from My Friends";
		List<String> result = songs.stream()
			.filter(song -> song.getTitle().equals(songTitle)) 	// filter for songs matching given title
			.map(song -> song.getArtist())						// map the stream from song to artist names
			.filter(artist -> !artist.equals("The Beatles"))	// filter out 'The Beatles' from artist names
			.collect(Collectors.toList());						// terminate stream by collecting into a List
	}
}