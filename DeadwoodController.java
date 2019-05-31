
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
    System.out.print("Repaint\n");
    deadwoodFrame.labelCurrentPlayer.setText(p.getName());
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
