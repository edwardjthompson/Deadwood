

import java.awt.event.MouseEvent;

public class MoveButtonMouseListener extends BaseDeadwoodMouseListener {
    private final String MOVE_SELECT_MSG = "Move is Selected\n";
    DeadwoodFrame deadwoodFrame;
    DeadwoodController deadwoodController;

    public MoveButtonMouseListener(DeadwoodFrame frame, DeadwoodController controller) {
        deadwoodFrame = frame;
        deadwoodController = controller;
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println(MOVE_SELECT_MSG);
        deadwoodController.buttonInput("m");
    }
}
