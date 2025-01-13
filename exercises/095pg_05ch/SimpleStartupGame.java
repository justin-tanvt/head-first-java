public class SimpleStartupGame {
  public static void main(String[] args) {
    int numOfGuesses = 0;
    GameHelper helper = new GameHelper();

    SimpleStartup startup = new SimpleStartup();
    int randomNum = (int) (Math.random() * 5);
    int[] location = {randomNum, randomNum + 1, randomNum + 2};
    startup.setLocationCells(location);

    boolean isAlive = true;
    while (isAlive) {
      int input = helper.getUserInput("enter a number");
      String result = startup.checkYourself(input);
      numOfGuesses++;

      if (result.equals("kill")) {
        isAlive = false;
        System.out.println("You took " + numOfGuesses + " guesses");
      }
    }
  }
}
