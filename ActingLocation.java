
import java.util.ArrayList;

public class ActingLocation extends Location {
  //private boolean hasScene;
  private Scene currentScene;
  private int numShots;
  private ArrayList<Role> listOfExtras;
  private boolean hasSceneBeenRevealed;
  private boolean hasSceneFinished;

  public ActingLocation(String name) {
    super(name);
    listOfExtras = new ArrayList<Role>();
    hasSceneFinished = false;
    hasSceneBeenRevealed = false;
  }

  //Only need to use this one, will give you available roles for it and the card on it
  public ArrayList<Role> getAvailableRole() {
    ArrayList<Role> open = currentScene.getAvailableRole();
    for(int i = 0; i < listOfExtras.size(); i++) {
      if(listOfExtras.get(i).take(null)) open.add(listOfExtras.get(i));
    }
    return open;
  }

  //Not sure what this one is for, might be redundent
  public ArrayList<Player> getActors() {
    ArrayList<Player> players = currentScene.getActors();
    for(int i = 0; i < listOfExtras.size(); i++) {
      if(listOfExtras.get(i).take(null)) players.add(listOfExtras.get(i).getPlayer());
    }
    return players;
  }

  public void removeShot() {
    numShots--;
  }

  public void setShots(int shots) {
    numShots = shots;
  }

  public void setScene(Scene scene) {
    hasSceneFinished = false;
    hasSceneBeenRevealed = false;
    currentScene = scene;
  }

  public void addRole(Role role) {
    listOfExtras.add(role);
  }

  public void revealScene() {
    hasSceneBeenRevealed = true;
  }

  public void finishScene() {
    hasSceneFinished = true;
  }

  public void resultOfAct(boolean actingResult, Player actor) {

  }

  public void printLocation() {
    System.out.println(name);
    if(listOfPlayer.size() > 0) {
      System.out.println("PLAYERS:");
      for(int i = 0; i < listOfPlayer.size(); i++) {
        listOfPlayer.get(i).printPlayer();
      }
      System.out.println();
    }
    if(hasSceneBeenRevealed) {
      if(hasSceneFinished) {
        System.out.println("The Scene in this Room is Shot for the Day!");
      }
      else {
        currentScene.printScene();
      }
    }
    else {
      System.out.println("There is an Unrevealed Scene in this Room!");
    }
    System.out.println("EXTRAS:");
    for(int i = 0; i < listOfExtras.size(); i++) {
      listOfExtras.get(i).printRole();
    }
    System.out.println();
  }

  public boolean hasSceneFinished() {
    return hasSceneFinished;
  }
}
