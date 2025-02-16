import java.util.ArrayList;

public class Startup {

  private ArrayList<String> locationCells;

  public void setLocationCells(ArrayList<String> locs) {
    this.locationCells = locs;
  }

  public String checkYourself(String userInput) {
    String result = "miss";

    int index = locationCells.indexOf(userInput);
    if (index >= 0) {   // miss = -1, hit otherwise
      locationCells.remove(index); // remove cell after hit

      if (locationCells.isEmpty()) { // all cells hit, none remaining
        result = "kill";
      } else {
        result = "hit";
      }
    }

    System.out.println(result);
    return result;
  }

}
