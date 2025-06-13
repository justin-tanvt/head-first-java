public class Song {

    private final String title;
    private final String artist;
    private final String genre;
    private final int year;
    private final int timesPlayed;

    // Practice for you! Create a constructor, all the getters and a toString()
    public Song(String title, String artist, String genre, int year, int timesPlayed) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        this.timesPlayed = timesPlayed;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }
    
    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    @Override
    public String toString() {
        return "Song:{" +
        "title=" + title + ", " +
        "artist=" + artist + ", " +
        "genre=" + genre + ", " +
        "year=" + year + ", " +
        "timesPlayed=" + timesPlayed + "}";
    }
}