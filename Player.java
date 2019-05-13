
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
  private String name;
  private int dollars;
  private int credits;
  private int rank;
  private int numRehearsals;
  private Role currentRole;
  private Location currentLocation;
  private Scanner input = new Scanner(System.in);

  public Player(String name, Location currentLocation) {
    this.name = name;
    this.dollars = 0;
    this.credits = 0;
    this.rank = 1;
    this.numRehearsals = 0;
    this.currentRole = null;
    this.currentLocation = currentLocation;
  }

  public void takeTurn() {
    System.out.print("Current ");
    printPlayer();
    System.out.println("Select one of the following options:");
    System.out.println("[1]Move [2]Act [3]Rehearse [m]Skip [s]kip");
    int num;
    String s = "m";
    int choice = 1;

    // s = input.nextLine();


//    if (input.hasNextInt()) {
//      choice = input.nextInt();
//      input.nextLine();
//    }
    switch (s) {
      case "m" :
        //move
        System.out.println("Move");
        break;

      case "a" :
        //act
        System.out.println("Act");
        break;

      case "r" :
        //rehearse
        System.out.println("rehearse");
        break;

      case "s" :
        //skip
        System.out.println("skip");

        break;

      default :
        System.out.println("Invalid choice");
//        choice = 0;
    }




//    input.close();

  }


  public void updateDollars(int value) {
    this.dollars += value;
  }

  public void updateCredits(int value) {
    this.credits += value;
  }

  public void rehearse() {
    this.numRehearsals++;
  }

  public void takeRole(Role takenRole) {

  }

  public void leaveRole() {
    currentRole = null;
  }

  //May become a return type boolean working with other functions
  public void act() {

  }

  public void printPlayer() {
    System.out.printf("Player: %s\n", name);
    System.out.printf("\tDollars: %s\n", dollars);
    System.out.printf("\tCredits: %s\n", credits);
    System.out.printf("\tRank: %s\n", rank);
    System.out.printf("\tRehersals: %s\n", numRehearsals);
    if (currentRole != null) System.out.printf("\tRole: %s\n", currentRole.getName());
    if (currentLocation != null) System.out.printf("\tLocation: %s\n", currentLocation.getName());
  }

  public void setCurrentLocation(Location currentLocation) {
    this.currentLocation = currentLocation;
  }

  public String getName() {
    return name;
  }

  public boolean starring() {
    return currentRole.isMain();
  }

  public int getRank() {
    return rank;
  }

  public int getDollars() {
    return dollars;
  }

  public int getCredits() {
    return credits;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }
}
