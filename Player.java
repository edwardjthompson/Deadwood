
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


  // Color of name, not needed
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_RESET = "\u001B[0m";

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
    System.out.print("******************************************\n");
    System.out.print("Current ");
    printPlayer();
    System.out.print("******************************************\n");
    System.out.println("Select one of the following options:");
    System.out.println("[m]ove [a]ct [r]ehearse [s]kip");

    if (currentLocation.getName().equals("Casting Office")) {
      System.out.println("You can also [u]pgrade!");
    }

    currentLocation.printAdjacent();

    String s = "m";
    int choice = 1;
    s = input.next();

    // if (input.hasNext()) {
    // }


//    if (input.hasNextInt()) {
//      choice = input.nextInt();
//      input.nextLine();
//    }
    switch (s) {
      case "m" :
        //move
        System.out.println("Move");
        move();
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
  }

  private void move() {
    int num = -1;
    int numOfAdjLocations = currentLocation.getAdjacentLocationSize();
    while (true) {
      currentLocation.printAdjacentOptions();
      if (input.hasNextInt()) {
        num = input.nextInt();
      }
      else input.next();
      if ((num >= 0) && (num < numOfAdjLocations)) {
        System.out.println(num);
        this.currentLocation = currentLocation.getAdjacentLocation(num);
        break;
      }
    }
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
    System.out.printf("Player: ", name);
    System.out.print(ANSI_RED + name + ANSI_RESET);
    System.out.printf("\n\tDollars: %s\n", dollars);
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

  public int getDollars() {
    return dollars;
  }

  public int getCredits() {
    return credits;
  }

  public int getRank() {
    return rank;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }
}
