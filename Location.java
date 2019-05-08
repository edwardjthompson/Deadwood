
import java.util.ArrayList;

public class Location {
  protected ArrayList<Location> adjacentLocationList;
  protected String name;

  public Location(String name) {
    this.name = name;
  }

  public void addPlayer(Player addedPlayer) {

  }

  public void removePlayer(Player removedPlayer) {

  }

  public void printLocation() {
    System.out.println(name);
    System.out.println();
  }
}
