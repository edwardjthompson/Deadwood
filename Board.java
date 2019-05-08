
import java.util.ArrayList;

public class Board {
  private ArrayList<Scene> listOfScenes;
  private Location[] locations;

  public Board(int size) {
    locations = new Location[size];
    listOfScenes = new ArrayList<Scene>();
  }

  public int getNumScenesRemaining() {
    return 0;
  }

  public void endDay() {

  }

  public void printBoard() {
    for(int i = 0; i < locations.length; i++) {
      locations[i].printLocation();
    }
  }

  public void addLocation(Location room) {
    int count = 0;
    while(count < locations.length) {
      if(locations[count] == null) {
        locations[count] = room;
        break;
      }
      count++;
    }
  }

  public void addScene(Scene scene) {
    listOfScenes.add(scene);
  }
}
