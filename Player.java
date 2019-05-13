
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
  private String nameColor;
  private Scanner input = new Scanner(System.in);



  // Color of name, not needed
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_RESET = "\u001B[0m";

  public Player(String name, Location currentLocation, String nameColor) {
    this.name = name;
    this.dollars = 0;
    this.credits = 0;
    this.rank = 1;
    this.numRehearsals = 0;
    this.currentRole = null;
    this.currentLocation = currentLocation;
    this.nameColor = nameColor;
  }

  public void takeTurn() {
    boolean turnComplete = false;
    String choice;

    System.out.print("******************************************\n");
    System.out.print("Current ");
    printPlayer();
    System.out.print("******************************************\n");
    System.out.println("Select one of the following options:");

    while(!turnComplete) {
      // If not in a role
      if (currentRole == null) {
        currentLocation.printAdjacent();
        System.out.print("[M]ove");
      }
      else {
        // If in a role
        System.out.print("[A]ct [R]ehearse");
      }

      if (currentLocation.getName().equals("Casting Office")) {
        // Able to upgrade
        System.out.print(" [U]pgrade!");
      }
      // Always able to skip
      System.out.print(" [S]kip\n");

      // Takes player input
      choice = input.next();

      switch (choice) {
        case "m" :
        case "M" :
          if (currentRole == null) {
            move();
            turnComplete = true;
          }
          break;

        case "a" :
        case "A" :
          if (currentRole != null) {
            act();
            turnComplete = true;
          }
          break;

        case "r" :
        case "R" :
          if (currentRole != null) {
            numRehearsals++;
            turnComplete = true;
          }
          break;

        case "s" :
        case "S" :
          break;

        default :
          System.out.println("Invalid choice");
      }

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
        // if scene not revealed, reveal
        if (!((ActingLocation) currentLocation).hasSceneFinished()) {
          // Scene has not finished
//          ((ActingLocation) currentLocation).revealScene();
          currentLocation.printLocation();
        }

        takeRole();
        break;
      }
    }
  }

  private void takeRole() {

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
    int budget = ((ActingLocation) currentLocation).getScene().getBudget();
    System.out.printf("Budget is %d\n" , budget);
  }

  public void printPlayer() {
    System.out.printf("Player: ", name);
    System.out.print(nameColor + name + ANSI_RESET);
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
