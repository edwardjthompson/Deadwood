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
    this.dollars = 0;
    this.credits = 0;
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

    while(!turnComplete) {
      // If not in a role
      if (currentRole == null) {
        turnChoices.add("m");
      }
      else {
        // If in a role
        turnChoices.add("a");
        turnChoices.add("r");
      }

      if (currentLocation.getName().equals("Casting Office")) {
        // Able to upgrade
        turnChoices.add("u");
      }
      // Always able to skip
      turnChoices.add("s");

      // Send options to controller
      choice = deadwoodController.playerOptions(turnChoices);

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
      }
    }
  }

  private void move() {
    int num = -2;
    int numOfAdjLocations = currentLocation.getAdjacentLocationSize();
    while (true) {
      num = deadwoodController.move(this);
      if (num == -2) {
        if (input.hasNextInt()) {
          num = input.nextInt();
        }
        else input.next();
      }
      if ((num >= -1) && (num < numOfAdjLocations)) {
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
      deadwoodController.repaintFrame();
    }
    else check = false;
    roleChoices.clear();
    roleChoices = location.getRoles(roleChoices);
    while (check) {
      choice = deadwoodController.role(this, roleChoices);
      if (choice == -2) {
        if (input.hasNextInt()) {
          choice = input.nextInt();
        }
      }

      if (choice < 0) {
        takenRole = null;
        break;
      }
      takenRole = location.selectRole(choice);

      if (takenRole == null);
      else {
        if (!takenRole.take(this)) {
          String message = "Your rank is not high enough to take this role.";
          deadwoodController.roleTaken(message);
        }
        else check = false;
      }
    }

    if(takenRole != null) {
      takeRole(takenRole);
    }
  }

  private void upgrade(CastingOffice location) {
    int num = -2;
    String payType = "";
    boolean check = false;
    ArrayList<String> upgradeOptions = location.availableUpgrades(this);
    // while (true) {
    //   num = deadwoodController.upgradeRank(upgradeOptions);
    //   System.out.println("NUM is " + num);
    //   break;
    //   // payType = deadwoodController.upgradePayment();
    //   // System.out.println("PayType is " + num);
    // }


    while (true) {
      if (num == -2) {
        num = deadwoodController.upgradeRank(upgradeOptions);
      }
      payType = "";
      check = false;
      if ((num >= -1) && (num <= 6)) {
        if(num == -1) break;

        while(!(payType.equals("d") || payType.equals("c") || payType.equals("D") || payType.equals("C"))) {
          payType = deadwoodController.upgradePayment();
          // System.out.printf("What payment type would you like to use?\n[d]ollars, [c]redits: ");
          // payType = input.next();

          check = location.selectUpgrade(this, num, payType.equals("d") || payType.equals("D"));

        }
        if(check) {
          break;
        }
        else {
        }

      }
    }
  }

  public void updateDollars(int value) {
    this.dollars += value;
  }

  public void updateCredits(int value) {
    this.credits += value;
  }

  private void takeRole(Role takenRole) {
    currentLocation.removePlayer(this);
    currentRole = takenRole;
  }

  public void leaveRole() {
    currentLocation.addPlayer(this);
    currentRole = null;
  }

  public void goToLocation(Location location) {
    if(currentRole != null) {
      currentRole.leave();
    }
    currentLocation.removePlayer(this);
    currentLocation = location;
    currentLocation.addPlayer(this);
  }

  //May become a return type boolean working with other functions
  private void act() {
    int budget = ((ActingLocation) currentLocation).getScene().getBudget();
    Dice die = new Dice();
    int[] dieRoll = die.roll(1);
    int total = dieRoll[0] + numRehearsals;

    if (total >= budget) {
      ((ActingLocation) currentLocation).resultOfAct(true, this);
    }
    else {
      ((ActingLocation) currentLocation).resultOfAct(false, this);
    }
    numRehearsals = 0;
  }

  public String getName() {
    return name;
  }

  public String getID() {
    return name + Integer.toString(rank);
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
