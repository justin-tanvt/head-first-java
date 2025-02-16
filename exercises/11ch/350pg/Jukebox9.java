import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Jukebox9 {
    public static void main(String[] args) {
        new Jukebox9().go();
    }

    public void go() {
        List<SongV4> songList = MockMoreSongs.getSongsV4();
        System.out.println(songList);

        songList.sort((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        System.out.println(songList);

        Set<SongV4> songSet = new HashSet<>(songList);
        System.out.println(songSet);
    }
}
