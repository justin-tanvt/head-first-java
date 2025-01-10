public class SimpleStartupTestDrive {
  public static void main (String[] args) {
    SimpleStartup sut = new SimpleStartup();
    
    int[] locations = {2, 3, 4};
    sut.setLocationCells(locations);

    int userGuess = 2;
    String result = sut.checkYourself(userGuess);

    String testResult = "failed";
    if (result.equals("hit")) {
      testResult = "passed";
    }

    System.out.println(testResult);
  }
}
