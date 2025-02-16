import java.util.Comparator;

public class ArtistCompare implements Comparator<SongV3> {
    @Override
    public int compare(SongV3 o1, SongV3 o2) {
        return o1.getArtist().compareTo(o2.getArtist());
    }
}
