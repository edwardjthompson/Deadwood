
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
      open.add(listOfExtras.get(i));
    }
    return open;
  }

  public Role selectRole(int num) {
    ArrayList<Role> arrayOfRoles = getAvailableRole();
    if(num >= arrayOfRoles.size()) return null;
    else return arrayOfRoles.get(num);
  }

  //Not sure what this one is for, might be redundent
  public ArrayList<Player> getActors() {
    ArrayList<Player> players = currentScene.getActors();
    for(int i = 0; i < listOfExtras.size(); i++) {
      if(!listOfExtras.get(i).take(null)) players.add(listOfExtras.get(i).getPlayer());
    }
    return players;
  }

  public void removeShot() {
    numShots--;
  }

  public void setShots(int shots) {
    numShots = shots;
  }

  public int getShots() {
    return numShots;
  }

  public void setScene(Scene scene) {
    hasSceneFinished = false;
    hasSceneBeenRevealed = false;
    currentScene = scene;
  }

  public Scene getScene() {
    return currentScene;
  }

  public void addRole(Role role) {
    listOfExtras.add(role);
  }

  public void revealScene() {
    hasSceneBeenRevealed = true;
  }

  public boolean finishScene() {
    hasSceneFinished = true;
    return currentScene.payout();
  }

  public void resultOfAct(boolean actingResult, Player actor) {
    if(actingResult) {
      numShots--;
      if(actor.starring()) {
        actor.updateCredits(2);
      }
      else {
        actor.updateCredits(1);
        actor.updateDollars(1);
      }
    }
    else {
      if(!actor.starring()) {
        actor.updateDollars(1);
      }
    }
    System.out.printf("Shots remaining: %d\n", numShots);
    if(numShots <= 0) {
      Player current;

      if(finishScene()) {
        for(int i = 0; i < listOfExtras.size(); i++) {
          current = listOfExtras.get(i).getPlayer();
          if(current != null) {
            current.updateDollars(listOfExtras.get(i).getRank());
            listOfExtras.get(i).leave();
          }
        }
      }
      else {
        for(int i = 0; i < listOfExtras.size(); i++) {
          current = listOfExtras.get(i).getPlayer();
          if(current != null) {
            listOfExtras.get(i).leave();
          }
        }
      }
    }
  }

  public void printActLocation() {
    int selectionNum = 0;
    System.out.println(name);
    if(hasSceneBeenRevealed) {
      if(hasSceneFinished) {
        System.out.println("The Scene in this Room is Shot for the Day!");
      }
      else {
        if (currentScene != null) {
          selectionNum = currentScene.printScene(selectionNum);
        }
      }
    }
    else {
      System.out.println("There is an Unrevealed Scene in this Room!");
    }
    System.out.println("EXTRAS:");
    for(int i = 0; i < listOfExtras.size(); i++) {
      System.out.printf("[%d]", selectionNum);
      selectionNum++;
      listOfExtras.get(i).printRole();
    }
//    currentScene.printScene();
    System.out.println();
  }

  public boolean hasSceneFinished() {
    return hasSceneFinished;
  }
}
