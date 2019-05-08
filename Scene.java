
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

  public ArrayList<Role> getAvailableRole() {
    return null;
  }

  //Not sure what this one is for, might be redundent
  public ArrayList<Player> getActors() {
    return null;
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
