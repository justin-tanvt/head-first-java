import java.util.ArrayList;

public class StartupTestDrive {
  public static void main (String[] args) {
    Startup sut = new Startup();
    
    ArrayList<String> locations = new ArrayList<>();
    locations.add("2");
    locations.add("3");
    locations.add("4");
    sut.setLocationCells(locations);

    String userGuess = "2";
    String result = sut.checkYourself(userGuess);

    String testResult = "failed";
    if (result.equals("hit")) {
      testResult = "passed";
    }

    System.out.println(testResult);
  }
}
