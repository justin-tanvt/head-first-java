import java.util.ArrayList;

public class StartupBust {

  private static final String[] STARTUP_NAMES = {"poniez", "hacqi", "cabista"};
  private static final int STARTUP_SIZE = 3;
  private static final int NUM_OF_GUESSES_SMALL = 18;

  private final GameHelper helper = new GameHelper();
  private final ArrayList<Startup> startups = new ArrayList<>();
  private int numOfGuesses = 0;

  public void setUpGame() {
    for (String name : STARTUP_NAMES) {
      Startup startup = new Startup();
      startup.setName(name);
      startup.setLocationCells(helper.placeStartup(STARTUP_SIZE));
      this.startups.add(startup);
    }
  }

  public void startPlaying() {
    while (!this.startups.isEmpty()) {
      String userGuess = helper.getUserInput("Enter a guess");
      checkUserGuess(userGuess);
    }
    finishGame();
  }

  public void checkUserGuess(String userGuess) {
    this.numOfGuesses++;
    String result = "miss";

    for (Startup startup : this.startups) {
      result = startup.checkYourself(userGuess);

      if (result.equals("hit")) {
        break;
      }

      if (result.equals("kill")) {
        this.startups.remove(startup);
        break;
      }
    }

    System.out.println(result);
  }

  public void finishGame() {
    System.out.println("All Startups are dead! Your stock is now worthless");
    if (this.numOfGuesses < NUM_OF_GUESSES_SMALL) {
      System.out.println("It only took you " + this.numOfGuesses + " guesses.");
      System.out.println("You got out before your options sank.");
    } else {
      System.out.println("Took you long enough. " + this.numOfGuesses + " guesses.");
      System.out.println("Fish are dancing with your options");
    }
  }

  public static void main(String[] args) {
    StartupBust game = new StartupBust();
    game.setUpGame();
    game.startPlaying();
  }

}
