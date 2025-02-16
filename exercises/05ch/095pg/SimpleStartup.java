public class SimpleStartup {

  private int[] locationCells;
  private int numOfHits = 0;

  public String checkYourself(int guess) {
    String result = "miss";

    for (int cell : this.locationCells) {
      if (guess == cell) {
        result = "hit";
        numOfHits++;
        break;
      }
    }

    if (numOfHits == this.locationCells.length) {
      result = "kill";
    }

    System.out.println(result);
    return result;
  }

  public void setLocationCells(int[] cellLocations) {
    this.locationCells = cellLocations;
  }

}
