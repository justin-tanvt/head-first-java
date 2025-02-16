import java.util.List;

public class Jukebox6 {
    public static void main(String[] args) {
        new Jukebox6().go();
    }

    public void go() {
        List<SongV3> songList = MockSongs.getSongsV3();
        System.out.println(songList);

        songList.sort((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        System.out.println(songList);

        songList.sort((o1, o2) -> o1.getArtist().compareTo(o2.getArtist()));
        System.out.println(songList);

        songList.sort((o1, o2) -> o1.getBpm() - o2.getBpm());
        System.out.println(songList);

        songList.sort(((o1, o2) -> o2.getTitle().compareTo(o1.getTitle())));
        System.out.println(songList);
    }
}
