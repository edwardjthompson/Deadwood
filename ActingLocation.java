
import java.util.ArrayList;

public class ActingLocation extends Location {
  private boolean hasScene;
  private Scene currentScene;
  private int numShots;
  private ArrayList<Role> listOfExtras;
  private boolean hasSceneBeenRevealed;
  private boolean hasSceneFinished;

  public ActingLocation(String name) {
    super(name);
    listOfExtras = new ArrayList<Role>();
  }

  public ArrayList<Role> getAvailableRole() {
    return null;
  }

  public void removeShot() {

  }

  public void setShots(int shots) {
    numShots = shots;
  }

  public void addRole(Role role) {
    listOfExtras.add(role);
  }

  public void revealScene() {

  }

  public void finishScene() {

  }

  public void resultOfAct(boolean actingResult, Player actor) {

  }

  public void printLocation() {
    System.out.println(name);
    System.out.println("EXTRAS:");
    for(int i = 0; i < listOfExtras.size(); i++) {
      listOfExtras.get(i).printRole();
    }
  }

  public boolean hasSceneFinished() {
    return hasSceneFinished;
  }
}
