
public class DeadwoodController {
  private Player currentplayer;
  private Deadwood deadwood;
  private DeadwoodFrame deadwoodFrame;

  public static void main(String[] args) {
    DeadwoodController deadwoodController = new DeadwoodController();



    // Getting numPlayer from main args
    int num = 0;
    try {
      if (args.length > 0) {
        num = Integer.parseInt(args[0]);
      }
    }
    catch(NumberFormatException e) {
      // Sets to 0 and calls setPlayerCount to set the value
      num = 0;
    }

    deadwoodController.initializeController(num, deadwoodController);
    // DeadwoodFrame deadwoodFrame = new DeadwoodFrame();
    // this.deadwoodFrame = deadwoodFrame;
    // deadwoodFrame.makeFrame();
    //
    // deadwood = new Deadwood(num, deadwoodController);


    // initializeGame
  }

  private void initializeController(int num, DeadwoodController deadwoodController) {
    deadwoodFrame = DeadwoodFrame.makeFrame();
    deadwood = new Deadwood(num, deadwoodController);
  }

  public void repaintFrame(Player p) {
    String name = p.getName();
    String dollars = Integer.toString(p.getDollars());
    String credits = Integer.toString(p.getCredits());
    String rank = Integer.toString(p.getRank());
    String rehearsals = Integer.toString(p.getNumRehearsals());

    String location;
    if (p.getLocation() == null) {
      location = "No current location";
    }
    else {
      location = p.getLocation().getName();
    }


    String role;
    if (p.getRole() == null) {
      role = "No current role";
    }
    else {
      role = p.getRole().getName();
    }



    String playerInfo = "<html>" + "<font color = " + name + ">" +
            "Current Player: " + name + "<br>" +
            "  Dollars: " + dollars + "<br>" +
            "\tCredits: " + credits + "<br>" +
            "\tRank: " + rank + "<br>" +
            "\tRehearsals: " + rehearsals + "<br>" +
            "\tLocation: " + location + "<br>" +
            "\tRole: " + role + "<br>" + "</font>" +
            "----------------------";

//    deadwoodFrame.labelCurrentPlayer.setText(p.getName());
//    System.out.print("Repaint\n");
    deadwoodFrame.labelCurrentPlayer.setText(playerInfo);
    // deadwoodFrame.updateCurrentPlayer(p);
    // deadwoodFrame.removeAll();
    // deadwoodFrame.revalidate();
    // deadwoodFrame.repaint();
  }

  // initializeGame

  // update currentplayer

  // update mainTurnOptions

  // updateBoard

  //


}
