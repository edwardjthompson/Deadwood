

import java.awt.event.MouseEvent;

public class RoleButtonMouseListener extends BaseDeadwoodMouseListener {
  DeadwoodFrame deadwoodFrame;
  DeadwoodController deadwoodController;
  int roleIndex;

  public RoleButtonMouseListener(DeadwoodFrame frame, DeadwoodController controller, int i) {
    deadwoodFrame = frame;
    deadwoodController = controller;
    roleIndex = i;

  }

  public void mouseClicked(MouseEvent e) {
    deadwoodController.buttonInput(roleIndex);
  }
}
