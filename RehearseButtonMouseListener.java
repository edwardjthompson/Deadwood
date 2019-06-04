

import java.awt.event.MouseEvent;

public class RehearseButtonMouseListener extends BaseDeadwoodMouseListener {
    private final String REHEARSE_SELECT_MSG = "Rehearse is Selected\n";
    DeadwoodFrame deadwoodFrame;
    DeadwoodController deadwoodController;

    public RehearseButtonMouseListener(DeadwoodFrame frame, DeadwoodController controller) {
        deadwoodFrame = frame;
        deadwoodController = controller;
    }

    public void mouseClicked(MouseEvent e) {
        //System.out.println(REHEARSE_SELECT_MSG);
        deadwoodController.buttonInput("r");
    }
}
