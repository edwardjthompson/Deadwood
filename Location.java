
import java.util.ArrayList;


//Be able to trade around players in this, as with roles and scenes
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

  public boolean hasSceneFinished() {
    return true;
  }

  public String getName() {
    return name;
  }
}
