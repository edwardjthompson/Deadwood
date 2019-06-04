

import java.awt.event.MouseEvent;

public class UpgradeButtonMouseListener extends BaseDeadwoodMouseListener {
  private final String UPGRADE_SELECT_MSG = "Upgrading is Selected\n";
  DeadwoodFrame deadwoodFrame;
  DeadwoodController deadwoodController;

  public UpgradeButtonMouseListener(DeadwoodFrame frame, DeadwoodController controller) {
    deadwoodFrame = frame;
    deadwoodController = controller;
  }

  public void mouseClicked(MouseEvent e) {
    //System.out.println(UPGRADE_SELECT_MSG);
    deadwoodController.buttonInput("u");
  }
}
