import java.util.List;
import java.util.stream.Collectors;

public class JukeboxStreams {
	public static void main(String[] args) {
		List<Song> songs = new Songs().getSongs();

		List<String> genres = songs.stream()
			.map(s -> s.getGenre())
			.distinct()
			.collect(Collectors.toList());
		System.out.println(genres);
	}
}