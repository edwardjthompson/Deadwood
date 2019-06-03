
import java.util.ArrayList;

public class Scene {
  private String name;
  private String description;
  private int budget;
  private ArrayList<Role> listOfRoles;
  private String id;

  public Scene(String name, String description, int budget, String id) {
    listOfRoles = new ArrayList<Role>();
    this.name = name;
    this.description = description;
    this.budget = budget;
    this.id = id;
  }

  public void addRole(Role role) {
    listOfRoles.add(role);
  }

  //DON'T USE THIS ONE! Use the one in Actinglocation as it contains this information
  public ArrayList<Role> getAvailableRole() {
    ArrayList<Role> open = new ArrayList<Role>();
    for(int i = 0; i < listOfRoles.size(); i++) {
      open.add(listOfRoles.get(i));
    }
    return open;
  }

  //Same as above

  public ArrayList<Player> getActors() {
    ArrayList<Player> players = new ArrayList<Player>();
    for(int i = 0; i < listOfRoles.size(); i++) {
      if(listOfRoles.get(i).getPlayer() != null) players.add(listOfRoles.get(i).getPlayer());
    }
    return players;
  }

  public boolean payout() {
    ArrayList<Player> players = getActors();
    if(players.size() > 0) {
      int rank = 0;
      int count = 0;
      Role[] order = new Role[listOfRoles.size()];

      while(rank < 6) {
        rank++;
        for(int i = 0; i < listOfRoles.size(); i++) {
          if(rank == listOfRoles.get(i).getRank()) {
            order[count] = listOfRoles.get(i);
            count++;
          }
        }
      }

      Dice dice = new Dice();
      int[] roll = dice.roll(budget);
      Player current;
      count = 0;

      for(int i = 0; i < roll.length; i++) {
        if(count < order.length) {
          current = order[count].getPlayer();
          if(current != null) {
            current.updateDollars(roll[i]);
          }
        }
        else {
          count = 0;
          current = order[count].getPlayer();
          if(current != null) {
            current.updateDollars(roll[i]);
          }
        }
      }

      for(int i = 0; i < order.length; i++) {
        current = order[i].getPlayer();
        if(current != null) {
          order[i].leave();
        }
      }

      return true;
    }
    return false;
  }

  public ArrayList<Image> printScene(boolean revealed) {
    ArrayList<Image> images = new ArrayList<Image>();
    if(revealed) {
      images.add(new Image("Back", 0, 0));
    }
    else {
      images.add(new Image(id, 0, 0));
    }
    for(int i = 0; i < listOfRoles.size(); i++) {
      if(listOfRoles.get(i).printRole() != null) {
        images.add(listOfRoles.get(i).printRole());
      }
    }
    return images;
  }

  public ArrayList<Role> getListOfRoles() {
    return listOfRoles;
  }

  public int getBudget() {
    return budget;
  }
}
