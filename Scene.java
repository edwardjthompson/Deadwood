
import java.util.ArrayList;

public class Scene {
  private String name;
  private String description;
  private int budget;
  private ArrayList<Role> listOfRoles;

  public Scene(String name, String description,
               int budget) {
    listOfRoles = new ArrayList<Role>();
    this.name = name;
    this.description = description;
    this.budget = budget;
  }

  public void addRole(Role role) {
    listOfRoles.add(role);
  }

  //DON'T USE THIS ONE! Use the one in Actinglocation as it contains this information
  public ArrayList<Role> getAvailableRole() {
    ArrayList<Role> open = new ArrayList<Role>();
    for(int i = 0; i < listOfRoles.size(); i++) {
      if(listOfRoles.get(i).take(null)) open.add(listOfRoles.get(i));
    }
    return open;
  }

  //Not sure what this one is for, might be redundant

  //Same as above

  public ArrayList<Player> getActors() {
    ArrayList<Player> players = new ArrayList<Player>();
    for(int i = 0; i < listOfRoles.size(); i++) {
      if(listOfRoles.get(i).take(null)) players.add(listOfRoles.get(i).getPlayer());
    }
    return players;
  }

  public void payout() {

  }

  public void printScene() {
    System.out.println(name);
    System.out.printf("BUDGET: $%d Million\n", budget);
    System.out.println(description);
    System.out.println("STARING ROLES:");
    for(int i = 0; i < listOfRoles.size(); i++) {
      listOfRoles.get(i).printRole();
    }
  }
}
