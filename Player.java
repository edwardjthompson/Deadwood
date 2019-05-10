
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
    System.out.println("[1]Move [2]Act [3]Rehearse [4]Skip");
    int num;
    String s;
    int choice = 1;


//    if (input.hasNextInt()) {
//      choice = input.nextInt();
//      input.nextLine();
//    }
    switch (choice) {
      case 1 :
        //move
        System.out.println("Move");
        break;

      case 2 :
        //act
        System.out.println("Act");
        break;

      case 3 :
        //rehearse
        System.out.println("rehearse");
        break;

      case 4 :
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

}
