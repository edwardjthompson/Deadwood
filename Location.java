import java.util.ArrayList;

public class Location {
  protected ArrayList<Location> adjacentLocationList;
  protected ArrayList<Player> listOfPlayer;
  protected String name;

  protected int playerx;
  protected int playery;

  public Location(String name, int px, int py) {
    adjacentLocationList = new ArrayList<Location>();
    listOfPlayer = new ArrayList<Player>();
    this.name = name;
    playerx = px;
    playery = py;
  }

  public void addPlayer(Player addedPlayer) {
    listOfPlayer.add(addedPlayer);
  }

  public void removePlayer(Player removedPlayer) {
    if(listOfPlayer.contains(removedPlayer)) {
      listOfPlayer.remove(removedPlayer);
    }
  }

  public void addAdjacent(Location addedLocation) {
    adjacentLocationList.add(addedLocation);
  }

  public Location getAdjacentLocation(int input) {
    return adjacentLocationList.get(input);
  }

  public int getAdjacentLocationSize() {
    return adjacentLocationList.size();
  }

  public ArrayList<Image> printLocation() {
    ArrayList<Image> images = new ArrayList<Image>();
    if(listOfPlayer.size() > 0) {
      for(int i = 0; i < listOfPlayer.size(); i++) {
        images.add(new Image(listOfPlayer.get(i).getID(), playerx + (45 * i), playery));
      }
    }
    return images;
  }

  public void printAdjacent() {
    System.out.printf("Adjacent Locations: ", name);
    for(int i = 0; i < adjacentLocationList.size(); i++) {
      String s = adjacentLocationList.get(i).getName();
      System.out.printf("%s ", s);
    }
    System.out.println();
  }

  // like printadjacent but adds [n] around each for choosing
  public void printAdjacentOptions() {
    for(int i = 0; i < adjacentLocationList.size(); i++) {
      String s = adjacentLocationList.get(i).getName();
      System.out.printf("[%d]%s ", i, s);
    }
    System.out.printf("\n\nSelect a Location (-1 to exit): ");
  }

  public boolean hasSceneFinished() {
    return true;
  }

  public String getName() {
    return name;
  }
}
