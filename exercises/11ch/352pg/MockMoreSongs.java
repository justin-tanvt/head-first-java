import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MockMoreSongs {

    private static final Path SONG_LIST_FILEPATH = Paths.get("..", "common", "SongListMore.txt");
    private static final String SONG_LIST_DELIMITER = ", ";
    private static final int SONG_LIST_NUMBER_OF_DETAILS = 3;

    public static List<SongV4> getSongsV4() {
        List<SongV4> songs = new ArrayList<>();
        try (Stream<String> stream = Files.lines(SONG_LIST_FILEPATH, StandardCharsets.UTF_8)) {
            stream
                    .map(row -> row.split(SONG_LIST_DELIMITER))
                    .filter(parts -> parts.length == SONG_LIST_NUMBER_OF_DETAILS)
                    .map(parts -> new SongV4(parts[0], parts[1], Integer.parseInt(parts[2])))
                    .forEach(songs::add);
        } catch (IOException x) {
            x.printStackTrace();
        }
        return songs;
    }
}
