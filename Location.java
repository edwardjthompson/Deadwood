
import java.util.ArrayList;

public class Location {
  protected ArrayList<Location> adjacentLocationList;
  protected ArrayList<Player> listOfPlayer;
  protected String name;

  public Location(String name) {
    adjacentLocationList = new ArrayList<Location>();
    listOfPlayer = new ArrayList<Player>();
    this.name = name;
  }

  public void addPlayer(Player addedPlayer) {
    listOfPlayer.add(addedPlayer);
  }

  public void removePlayer(Player removedPlayer) {
    if(listOfPlayer.contains(removedPlayer)) {
      listOfPlayer.remove(removedPlayer);
    }
  }

  public void printLocation() {
    System.out.println(name);
    if(listOfPlayer.size() > 0) {
      System.out.println("PLAYERS:");
      for(int i = 0; i < listOfPlayer.size(); i++) {
        listOfPlayer.get(i).printPlayer();
      }
    }
    System.out.println();
  }

  public boolean hasSceneFinished() {
    return true;
  }
}
