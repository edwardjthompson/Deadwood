

import java.awt.event.MouseEvent;

public class AdjacentLocationButtonMouseListener extends BaseDeadwoodMouseListener {
  DeadwoodFrame deadwoodFrame;
  DeadwoodController deadwoodController;
  int adjacentLocationIndex;

  public AdjacentLocationButtonMouseListener(DeadwoodFrame frame, DeadwoodController controller, int i) {
    deadwoodFrame = frame;
    deadwoodController = controller;
    adjacentLocationIndex = i;

  }

  public void mouseClicked(MouseEvent e) {
    deadwoodController.buttonInput(adjacentLocationIndex);
  }
}
