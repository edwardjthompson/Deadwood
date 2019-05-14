
public class CastingOffice extends Location {

  public CastingOffice(String name) {
    super(name);
  }

  public void availableUpgrades(Player player) {
    int rank = player.getRank();
    int dollars = player.getDollars();
    int credits = player.getCredits();

    switch(rank) {
      case(1):
        if(dollars >= 4 || credits >= 5) System.out.print(">");
        else System.out.print("x");
        System.out.println(" Rank 2: 4 Dollars, 5 Credits");
      case(2):
        if(dollars >= 10 || credits >= 10) System.out.print(">");
        else System.out.print("x");
        System.out.println(" Rank 3: 10 Dollars, 10 Credits");
      case(3):
        if(dollars >= 18 || credits >= 15) System.out.print(">");
        else System.out.print("x");
        System.out.println(" Rank 4: 18 Dollars, 15 Credits");
      case(4):
        if(dollars >= 28 || credits >= 20) System.out.print(">");
        else System.out.print("x");
        System.out.println(" Rank 5: 28 Dollars, 20 Credits");
      case(5):
        if(dollars >= 40 || credits >= 25) System.out.print(">");
        else System.out.print("x");
        System.out.println(" Rank 6: 40 Dollars, 25 Credits");
    }
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
