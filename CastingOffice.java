import java.util.ArrayList;

public class CastingOffice extends Location {

  public CastingOffice(String name, int x, int y) {
    super(name, x, y);
  }

  public ArrayList<String> availableUpgrades(Player player) {
    int rank = player.getRank();
    int dollars = player.getDollars();
    int credits = player.getCredits();
    ArrayList<String> upgradeOptions = new ArrayList<>();

    switch(rank) {
      case(1):
        if(dollars >= 4 || credits >= 5)  {
          upgradeOptions.add("  2  ");
        }
        if(dollars >= 10 || credits >= 10) {
          upgradeOptions.add("  3  ");
        }
      case(3):
        if(dollars >= 18 || credits >= 15) {
          upgradeOptions.add("  4  ");
        }
      case(4):
        if(dollars >= 28 || credits >= 20) {
          upgradeOptions.add("  5  ");
        }
      case(5):
        if(dollars >= 40 || credits >= 25) {
          upgradeOptions.add("  6  ");
        }
    }

    upgradeOptions.add("Cancel Upgrade");

    return upgradeOptions;
  }

  public boolean selectUpgrade(Player player, int selectedRank, boolean payInDollars) {
    int dollars = player.getDollars();
    int credits = player.getCredits();
    int subDollars = 0;
    int subCredits = 0;
    boolean success = false;

    if(selectedRank > player.getRank()) {
      switch(selectedRank) {
        case(2):
          if(dollars >= 4 && payInDollars) {
            success = true;
            subDollars = -4;
          }
          else if(credits >= 5) {
            success = true;
            subCredits = -5;
          }
          break;
        case(3):
          if(dollars >= 10 && payInDollars) {
            success = true;
            subDollars = -10;
          }
          else if(credits >= 10) {
            success = true;
            subCredits = -10;
          }
          break;
        case(4):
          if(dollars >= 18 && payInDollars) {
            success = true;
            subDollars = -18;
          }
          else if(credits >= 15) {
            success = true;
            subCredits = -15;
          }
          break;
        case(5):
          if(dollars >= 28 && payInDollars) {
            success = true;
            subDollars = -28;
          }
          else if(credits >= 20) {
            success = true;
            subCredits = -20;
          }
          break;
        case(6):
          if(dollars >= 40 && payInDollars) {
            success = true;
            subDollars = -40;
          }
          else if(credits >= 25) {
            success = true;
            subCredits = -25;
          }
          break;
      }
    }

    if(success) {
      player.updateDollars(subDollars);
      player.updateCredits(subCredits);
      player.setRank(selectedRank);
    }

    return success;
  }
}
