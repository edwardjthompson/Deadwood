

import java.awt.event.MouseEvent;

public class ActButtonMouseListener extends BaseDeadwoodMouseListener {
  private final String ACT_SELECT_MSG = "Acting is Selected\n";
  DeadwoodFrame deadwoodFrame;

  public ActButtonMouseListener(DeadwoodFrame frame) {
    deadwoodFrame = frame;
  }

  public void mouseClicked(MouseEvent e) {
      //System.out.println(ACT_SELECT_MSG);
      deadwoodFrame.labelCurrentPlayer.setText(ACT_SELECT_MSG);
  }
}
