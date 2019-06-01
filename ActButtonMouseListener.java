

import java.awt.event.MouseEvent;

public class ActButtonMouseListener extends BaseDeadwoodMouseListener {
  private final String ACT_SELECT_MSG = "Acting is Selected\n";
  DeadwoodFrame deadwoodFrame;
  DeadwoodController deadwoodController;

  public ActButtonMouseListener(DeadwoodFrame frame, DeadwoodController controller) {
    deadwoodFrame = frame;
    deadwoodController = controller;
  }

  public void mouseClicked(MouseEvent e) {
      System.out.println(ACT_SELECT_MSG);
      deadwoodController.buttonInput("a");

  }
}
