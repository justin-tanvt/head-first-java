import java.util.List;
import java.util.stream.Collectors;

public class JukeboxStreams {
	public static void main(String[] args) {
		List<Song> songs = new Songs().getSongs();

		List<Song> songsByBeatles = songs.stream()
			.filter(s -> s.getArtist().toLowerCase().contains("beatles"))
			.collect(Collectors.toList());
		System.out.println(songsByBeatles);

		List<Song> songsStartingWithH = songs.stream()
			.filter(s -> s.getTitle().startsWith("H"))
			.collect(Collectors.toList());
		System.out.println(songsStartingWithH);

		List<Song> songsAfter1995 = songs.stream()
			.filter(s -> s.getYear() > 1995)
			.collect(Collectors.toList());
		System.out.println(songsAfter1995);
	}
}