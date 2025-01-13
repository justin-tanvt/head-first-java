import java.util.ArrayList;

public class StartupGame {
  public static void main(String[] args) {
    int numOfGuesses = 0;
    GameHelper helper = new GameHelper();

    Startup startup = new Startup();
    int randomNum = (int) (Math.random() * 5);
    ArrayList<String> location = new ArrayList<>();
    location.add(String.valueOf(randomNum));
    location.add(String.valueOf(randomNum + 1));
    location.add(String.valueOf(randomNum + 2));
    startup.setLocationCells(location);

    boolean isAlive = true;
    while (isAlive) {
      String input = helper.getUserInput("enter a number");
      String result = startup.checkYourself(input);
      numOfGuesses++;

      if (result.equals("kill")) {
        isAlive = false;
        System.out.println("You took " + numOfGuesses + " guesses");
      }
    }
  }
}
