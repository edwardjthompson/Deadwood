import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.ImageIcon;


public class DeadwoodFrame extends JFrame {
    public JLabel labelGameBoard;
    public JLabel labelCurrentPlayer;

    public JButton buttonAct;
    public JButton buttonRehearse;
    public JButton buttonMove;
    public JButton buttonSkip;
    public JButton buttonUpgrade;
    public ArrayList<JButton> buttonLocations = new ArrayList<>();
    public ArrayList<JButton> buttonRoles = new ArrayList<>();

    public JLayeredPane paneDeadwood;

    private Icons icons;
    public ArrayList<JLabel> imageHolders = new ArrayList<JLabel>();
    public ImageIcon iconGameBoard;

    private static final String DEADWOOD_TITLE = "Deadwood";
    private static final String GAME_BOARD_IMAGE = "board.jpg";
    private static final String MENU_LABEL_TEXT = "MENU";
    private static final String ACT_BUTTON_TEXT = "ACT";
    private static final String SKIP_BUTTON_TEXT = "SKIP";
    private static final String UPGRADE_BUTTON_TEXT = "UPGRADE";
    private static final String REHEARSE_BUTTON_TEXT = "REHEARSE";
    private static final String MOVE_BUTTON_TEXT = "MOVE";

    // Things I have added
    private String name = "Name";
    private String playerName = "<html>" + name + "<br>" + "newline";
    private static DeadwoodFrame deadwoodFrame;
    private DeadwoodController deadwoodController;

    public DeadwoodFrame(DeadwoodController controller) {
        super(DEADWOOD_TITLE);
        deadwoodController = controller;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeLabels();
        initializeButtons();
        initializeDeadwoodPane();
    }

    private void initializeLabels() {
        icons = new Icons();
        setupGameBoardLabel();
        //setupCardLabel();
        //setupPlayerLabel();
//        setupMenuLabel();
        setupCurrentPlayerLabel();
        // updateCurrentPlayer();
    }

    private void setupGameBoardLabel() {
        labelGameBoard = new JLabel();
        iconGameBoard = new ImageIcon(GAME_BOARD_IMAGE);
        labelGameBoard.setIcon(iconGameBoard);
        labelGameBoard.setBounds(0, 0, iconGameBoard.getIconWidth(), iconGameBoard.getIconHeight());
        setSize(iconGameBoard.getIconWidth() + 250, iconGameBoard.getIconHeight()); // Set the size of the GUI
    }

    public void setupImageLabel(String path, int x, int y, boolean priority) {
        JLabel labelCard = new JLabel();
        ImageIcon cardIcon = icons.getIcon(path);
        labelCard.setIcon(cardIcon);
        labelCard.setBounds(x, y, cardIcon.getIconWidth(), cardIcon.getIconHeight());
        //labelCard.setOpaque(true);
        imageHolders.add(labelCard);
        if(priority) {
          paneDeadwood.add(labelCard, new Integer(2));
        }
        else {
          paneDeadwood.add(labelCard, new Integer(1));
        }
        //labelCard.setVisible(true);
    }
/*
    private void setupPlayerLabel(String path, int x, int y) {
        labelPlayer = new JLabel();
        //Same as above!
        ImageIcon playerDiceIcon = new ImageIcon(path);
        labelPlayer.setIcon(playerDiceIcon);
        labelPlayer.setBounds(x, y, playerDiceIcon.getIconWidth(), playerDiceIcon.getIconHeight());
//        labelPlayer.setBounds(114,227,46,46);
    }
*/

    public void updateCurrentPlayer(Player currentPlayer) {
        name = currentPlayer.getName();

        labelCurrentPlayer.setText(name);
    }

    private void setupCurrentPlayerLabel() {
        labelCurrentPlayer = new JLabel(playerName);
        labelCurrentPlayer.setBounds(iconGameBoard.getIconWidth() + 10, 0, 180, 130);
    }

//    private void setupMenuLabel() {
//        labelMenu = new JLabel(MENU_LABEL_TEXT);
//        labelMenu.setBounds(iconGameBoard.getIconWidth() + 65, 130, 100, 20);
//    }

    private void initializeButtons() {
        setupActButton();
        setupRehearseButton();
        setupMoveButton();
        setupSkipButton();
        setupUpgradeButton();
    }

    private void setupActButton() {
        buttonAct = new JButton(ACT_BUTTON_TEXT);
        buttonAct.setBackground(Color.white);
        buttonAct.setBounds(iconGameBoard.getIconWidth() + 10, 180, 220, 50);
        buttonAct.addMouseListener(new ActButtonMouseListener(this, deadwoodController));
    }

    private void setupRehearseButton() {
        buttonRehearse = new JButton(REHEARSE_BUTTON_TEXT);
        buttonRehearse.setBackground(Color.white);
        buttonRehearse.setBounds(iconGameBoard.getIconWidth() + 10, 230, 220, 50);
        buttonRehearse.addMouseListener(new RehearseButtonMouseListener(this, deadwoodController));
    }

    private void setupMoveButton() {
        buttonMove = new JButton(MOVE_BUTTON_TEXT);
        buttonMove.setBackground(Color.white);
        buttonMove.setBounds(iconGameBoard.getIconWidth() + 10, 130, 220, 50);
        buttonMove.addMouseListener(new MoveButtonMouseListener(this, deadwoodController));
    }

    private void setupUpgradeButton() {
        buttonUpgrade = new JButton(UPGRADE_BUTTON_TEXT);
        buttonUpgrade.setBackground(Color.white);
        buttonUpgrade.setBounds(iconGameBoard.getIconWidth() + 10, 280, 220, 50);
        buttonUpgrade.addMouseListener(new UpgradeButtonMouseListener(this, deadwoodController));
    }

    private void setupSkipButton() {
        buttonSkip = new JButton(SKIP_BUTTON_TEXT);
        buttonSkip.setBackground(Color.white);
        buttonSkip.setBounds(iconGameBoard.getIconWidth() + 10, 330, 220, 50);
        buttonSkip.addMouseListener(new SkipButtonMouseListener(this, deadwoodController));
    }

    public void initializeDeadwoodPane() {
        paneDeadwood = getLayeredPane();
        paneDeadwood.add(labelGameBoard, new Integer(0)); // Add the board to the lowest layer
        //paneDeadwood.add(labelCard, new Integer(1)); // Add the card to the lower layer
        //paneDeadwood.add(labelPlayer, new Integer(3));
        paneDeadwood.add(labelCurrentPlayer, new Integer(3));

        paneDeadwood.add(buttonAct, new Integer(2));
        paneDeadwood.add(buttonRehearse, new Integer(2));
        paneDeadwood.add(buttonMove, new Integer(2));
        paneDeadwood.add(buttonSkip, new Integer(2));
        paneDeadwood.add(buttonUpgrade, new Integer(2));

    }

    public void positionButton(JButton b, int x, int y, int w, int h) {
        b.setBounds(iconGameBoard.getIconWidth() + x, y, w, h);
        b.setVisible(true);
    }

    public void removeButtons() {
        buttonMove.setVisible(false);
        buttonAct.setVisible(false);
        buttonRehearse.setVisible(false);
        buttonUpgrade.setVisible(false);
        buttonSkip.setVisible(false);
        int size = buttonLocations.size();
        System.out.println("bLocations Size: " + size);
        for (int i = 0; i < size; i++) {
            buttonLocations.get(i).setVisible(false);
        }
        size = buttonRoles.size();
        for (int i = 0; i < size; i++) {
            buttonRoles.get(i).setVisible(false);
        }
    }

    public void refreshScreen() {
      for(int i = 0; i < imageHolders.size(); i++) {
        paneDeadwood.remove(paneDeadwood.getIndexOf(imageHolders.get(i)));
      }
      imageHolders.clear();
      revalidate();
      repaint();
    }

    public static DeadwoodFrame makeFrame(DeadwoodController controller) {
      DeadwoodFrame board = new DeadwoodFrame(controller);
      deadwoodFrame = board;
      board.setVisible(true);

      return board;
    }
}
