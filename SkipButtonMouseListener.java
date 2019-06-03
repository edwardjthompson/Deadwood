

import java.awt.event.MouseEvent;

public class SkipButtonMouseListener extends BaseDeadwoodMouseListener {
  private final String SKIP_SELECT_MSG = "Skipping is Selected\n";
  DeadwoodFrame deadwoodFrame;
  DeadwoodController deadwoodController;

  public SkipButtonMouseListener(DeadwoodFrame frame, DeadwoodController controller) {
    deadwoodFrame = frame;
    deadwoodController = controller;
  }

  public void mouseClicked(MouseEvent e) {
    System.out.println(SKIP_SELECT_MSG);
    deadwoodController.buttonInput("s");
//    deadwoodFrame.labelCurrentPlayer.setText(ACT_SELECT_MSG);
  }
}