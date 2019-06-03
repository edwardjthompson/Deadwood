
import java.util.ArrayList;

public class ActingLocation extends Location {
  private Scene currentScene;
  private int numShots;
  private int totalShots;
  private ArrayList<Role> listOfExtras;
  private boolean hasSceneBeenRevealed;
  private boolean hasSceneFinished;

  private int scenex;
  private int sceney;

  private int shotx;
  private int shoty;
  private String shotDirection;

  public ActingLocation(String name, int px, int py, int sx, int sy) {
    super(name, px, py);
    listOfExtras = new ArrayList<Role>();
    hasSceneFinished = false;
    hasSceneBeenRevealed = false;
    scenex = sx;
    sceney = sy;
  }

  //Only need to use this one, will give you available roles for it and the card on it
  private ArrayList<Role> getAvailableRole() {
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

  public void setShots(int shots, String shotD, int sx, int sy) {
    numShots = shots;
    totalShots = shots;
    shotx = sx;
    shoty = sy;
    shotDirection = shotD;
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
    numShots = totalShots;
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

  public ArrayList<Image> printLocation() {
    ArrayList<Image> images = new ArrayList<Image>();
    if(!hasSceneFinished) {
      if (currentScene != null) {
        ArrayList<Image> sceneImages = currentScene.printScene(hasSceneBeenRevealed);
        for(int i = 0; i < sceneImages.size(); i++) {
          sceneImages.get(i).setX(sceneImages.get(i).getX() + scenex);
          sceneImages.get(i).setY(sceneImages.get(i).getY() + sceney);
        }
        images.addAll();
      }
    }
    for(int i = 0; i < listOfExtras.size(); i++) {
      images.add(listOfExtras.get(i).printRole());
    }
    if(listOfPlayer.size() > 0) {
      for(int i = 0; i < listOfPlayer.size(); i++) {
        images.add(new Image(listOfPlayer.get(i).getName(), playerx + (45 * i), playery));
      }
    }
    //Shot counters add 53 in whichever direction shots are going
    return images;
  }

  public boolean hasSceneFinished() {
    return hasSceneFinished;
  }
}
