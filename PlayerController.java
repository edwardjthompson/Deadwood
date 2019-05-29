public class PlayerController {
  private DeadwoodFrame deadwoodFrame;

  public PlayerController(DeadwoodFrame deadwoodFrame) {
    this.deadwoodFrame = deadwoodFrame;
  }


  public void takePlayer(Player currentPlayer) {
    Player thisPlayer = currentPlayer;
    System.out.printf("TAKEPLAYER: %s\n", thisPlayer.getName());
    deadwoodFrame.updateCurrentPlayer(currentPlayer);
    // deadwoodFrame.initializeDeadwoodPane();
    // deadwoodFrame.setVisible();
  }
}
