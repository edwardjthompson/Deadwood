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
  private static final String ANSI_RESET = "\u001B[0m";
  private ArrayList<String> turnChoices = new ArrayList<>();
  private ArrayList<String> roleChoices = new ArrayList<>();
  private DeadwoodController deadwoodController;

  public Player(String name, Location currentLocation, String nameColor, DeadwoodController deadwoodController) {
    this.name = name;
    this.dollars = 100;
    this.credits = 100;
    this.rank = 1;
    this.numRehearsals = 0;
    this.currentRole = null;
    this.currentLocation = currentLocation;
    this.nameColor = nameColor;
    this.currentLocation.addPlayer(this);
    this.deadwoodController = deadwoodController;
  }

  public void takeTurn() {
    boolean turnComplete = false;
    String choice = "notSet";
    turnChoices.clear();


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
        turnChoices.add("m");
      }
      else {
        // If in a role
        System.out.print("[A]ct [R]ehearse");
        turnChoices.add("a");
        turnChoices.add("r");
      }

      if (currentLocation.getName().equals("Casting Office")) {
        // Able to upgrade
        System.out.print(" [U]pgrade!");
        turnChoices.add("u");
      }
      // Always able to skip
      System.out.print(" [S]kip\n");
      turnChoices.add("s");

      // Send options to controller
      choice = deadwoodController.playerOptions(turnChoices);

      System.out.print("\nSelect an option: ");

      // Takes player input
      if (choice.equals("notSet")) {
        choice = input.next();
      }

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

        case "q" :
        case "Q" :
          System.exit(0);

        default :
          System.out.println("Invalid choice\n");
      }
      if (turnComplete == true) {
        System.out.printf("\n--------Next player--------\n\n");
      }
    }
  }

  private void move() {
    int num = -2;
    int numOfAdjLocations = currentLocation.getAdjacentLocationSize();
    while (true) {
      currentLocation.printAdjacentOptions();
      num = deadwoodController.move(this);
      if (num == -2) {
        if (input.hasNextInt()) {
          num = input.nextInt();
        }
        else input.next();
      }
      if ((num >= -1) && (num < numOfAdjLocations)) {
        System.out.println(num);
        if(num == -1) break;
        goToLocation(currentLocation.getAdjacentLocation(num));
        if(currentLocation instanceof ActingLocation) {
          getRoleOptions(((ActingLocation) currentLocation));
        }
        break;
      }
    }
  }

  private void getRoleOptions(ActingLocation location) {
    Role takenRole = null;
    int choice = -2;
    boolean check = true;
    // if scene not revealed, reveal
    if (!location.hasSceneFinished()) {
      // Scene has not finished
      location.revealScene();
      location.printActLocation();
      // New for GUI
//      roleChoices = location.getRoles(roleChoices);
//      int num = deadwoodController.role(this, roleChoices);
//      choice = num;
//      System.out.println("Selection: " + num);
    }
      roleChoices = location.getRoles(roleChoices);
    while (check) {
      choice = deadwoodController.role(this, roleChoices);
      System.out.printf("Select a role (-1 to exit): ");
      if (choice == -2) {
        if (input.hasNextInt()) {
          choice = input.nextInt();
        }
      }
//      else check = false;

      if (choice < 0) {
        takenRole = null;
        break;
      }
      takenRole = location.selectRole(choice);

      if (takenRole == null) {
        System.out.println("That was not an option!");
      }
      else {
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
    String payType = "";
    boolean check = false;
    System.out.printf("\nUpgrades: (x means you do not have the funds to " +
                      "purchase, > means it is available)\n");
    location.availableUpgrades(this);
    while (true) {
      System.out.printf("\nSelect a rank (-1 to exit): ");
      if (input.hasNextInt()) {
        num = input.nextInt();
      }
      else input.next();
      if ((num >= -1) && (num <= 6)) {
        if(num == -1) break;

        while(!(payType.equals("d") || payType.equals("c") || payType.equals("D") || payType.equals("C"))) {
          System.out.printf("What payment type would you like to use?\n[d]ollars, [c]redits: ");
          payType = input.next();
        }

        check = location.selectUpgrade(this, num, payType.equals("d") || payType.equals("D"));

        if(check) {
          System.out.printf("You have been upgraded to rank: %d\n", rank);
          break;
        }
        else {
          System.out.printf("You do not have the funds to be upgraded to " +
                            "that rank, your rank remains: %d\n", rank);
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

  private void takeRole(Role takenRole) {
    currentRole = takenRole;
  }

  public void leaveRole() {
    currentRole = null;
  }

  public void goToLocation(Location location) {
    currentLocation.removePlayer(this);
    currentLocation = location;
    currentLocation.addPlayer(this);
  }

  //May become a return type boolean working with other functions
  private void act() {
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
  }

  private void printPlayer() {
    System.out.printf("Player: ", name);
    System.out.print(nameColor + name + ANSI_RESET);
    System.out.printf("\n\tDollars: %s\n", dollars);
    System.out.printf("\tCredits: %s\n", credits);
    System.out.printf("\tRank: %s\n", rank);
    System.out.printf("\tRehearsals: %s\n", numRehearsals);
    if (currentRole != null) System.out.printf("\tRole: %s\n", currentRole.getName());
    if (currentLocation != null)  {
      System.out.printf("\tLocation: %s", currentLocation.getName());
    }
    if (currentLocation instanceof ActingLocation) {
      System.out.printf(" (Shots remaining: %d)\n", ((ActingLocation) currentLocation).getShots());
    }
    else System.out.println();
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

  public int getNumRehearsals() {
    return numRehearsals;
  }

  public Location getLocation() {
    return currentLocation;
  }

  public Role getRole() {
    return currentRole;
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

  public ArrayList<String> getTurnChoices() {
    return turnChoices;
  }
}
