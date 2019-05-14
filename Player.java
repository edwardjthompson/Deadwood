
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

      System.out.print("\nSelect an option: ");

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

        case "u" :
        case "U" :
          upgrade(((CastingOffice) currentLocation));
          turnComplete = true;
          break;

        case "s" :
        case "S" :
          turnComplete = true;
          break;

        default :
          System.out.println("Invalid choice");
      }

    }
  }

  private void move() {
    int num = -2;
    int numOfAdjLocations = currentLocation.getAdjacentLocationSize();
    while (true) {
      currentLocation.printAdjacentOptions();
      if (input.hasNextInt()) {
        num = input.nextInt();
      }
      else input.next();
      if ((num >= -1) && (num < numOfAdjLocations)) {
        System.out.println(num);
        if(num == -1) break;
        this.currentLocation = currentLocation.getAdjacentLocation(num);
        if(currentLocation instanceof ActingLocation) {
          getRoleOptions(((ActingLocation) currentLocation));
        }
        break;
      }
    }
  }

  private void getRoleOptions(ActingLocation location) {
    Role takenRole = null;
    int choice = -1;
    boolean check = true;
    // if scene not revealed, reveal
    if (!location.hasSceneFinished()) {
      // Scene has not finished
     location.revealScene();
      location.printLocation();
    }
    while (check) {
      System.out.printf("Select a role (-1 to exit): ");
      if (input.hasNextInt()) {
        choice = input.nextInt();
      }

      if (choice < 0) {
        takenRole = null;
        break;
      }
      // System.out.printf("Selecting Role: %d\n", choice);
      takenRole = location.selectRole(choice);

      if (takenRole == null) {
        System.out.println("That was not an option!");
      }
      else {
        //if (takenRole.getPlayer() != null) {
        if (!takenRole.take(this)) {
          System.out.println("Role already taken, or Rank too high");
        }
        else check = false;
      }
    }

    takeRole(takenRole);
  }

  private void upgrade(CastingOffice location) {
    int num = -2;
    boolean check = false;
    System.out.printf("\nUpgrades: (x means you do not have the funds to purchase, > means it is available)\n");
    location.availableUpgrades(this);
    while (true) {
      System.out.println();
      System.out.printf("Select a rank (-1 to exit): ");
      if (input.hasNextInt()) {
        num = input.nextInt();
      }
      else input.next();
      if ((num >= -1) && (num <= 6)) {
        if(num == -1) break;
        check = location.selectUpgrade(this, num);

        if(check) {
          System.out.printf("You have been upgraded to rank: %d\n", rank);
          break;
        }
        else {
          System.out.printf("You do not have the funds to be upgraded to that rank, your rank remains: %d\n", rank);
        }
      }
    }
    System.out.println();
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
    currentRole = takenRole;
    //takenRole.take(this);
  }

  public void leaveRole() {
    currentRole = null;
  }

  //May become a return type boolean working with other functions
  public void act() {
    int budget = ((ActingLocation) currentLocation).getScene().getBudget();
    System.out.printf("Budget is %d\n" , budget);
    Dice die = new Dice();
    int[] dieRoll = die.roll(1);
    int total = dieRoll[0] + numRehearsals;
    System.out.printf("Roll: %d, Rehearse: %d,", dieRoll[0], numRehearsals);
    System.out.printf(" Total: %d, Budget: %d\n", total, budget);

    if (total >= budget) {
      System.out.printf("Act was successful!\n");
      ((ActingLocation) currentLocation).resultOfAct(true, this);
    }
    else {
      System.out.printf("Act was unsuccessful.\n");
      ((ActingLocation) currentLocation).resultOfAct(false, this);
    }
    numRehearsals = 0;
    // Acting. resultOfAct
  }

  public void printPlayer() {
    System.out.printf("Player: ", name);
    System.out.print(nameColor + name + ANSI_RESET);
    System.out.printf("\n\tDollars: %s\n", dollars);
    System.out.printf("\tCredits: %s\n", credits);
    System.out.printf("\tRank: %s\n", rank);
    System.out.printf("\tRehersals: %s\n", numRehearsals);
    if (currentRole != null) System.out.printf("\tRole: %s\n", currentRole.getName());
    if (currentLocation != null)  {
      System.out.printf("\tLocation: %s", currentLocation.getName());
    }
    if (currentLocation instanceof ActingLocation) {
      System.out.printf(" (Shots remaining: %d)\n", ((ActingLocation) currentLocation).getShots());
    }
    else System.out.println();
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

  public int getScore() {
    return dollars + credits + (5*rank);
  }
}
