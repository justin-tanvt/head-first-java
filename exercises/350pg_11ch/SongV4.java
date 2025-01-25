public class SongV4 implements Comparable<SongV4> {
    private String title;
    private String artist;
    private int bpm;

    @Override
    public boolean equals(Object obj) {
        SongV4 other = (SongV4) obj;
        return title.equals(other.title);
    }

    public int hashCode() {
        return title.hashCode();
    }

    public SongV4(String title, String artist, int bpm) {
        this.title = title;
        this.artist = artist;
        this.bpm = bpm;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getBpm() {
        return bpm;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int compareTo(SongV4 o) {
        return title.compareTo(o.title);
    }
}
