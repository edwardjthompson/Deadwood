import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;


public class DeadwoodFrame extends JFrame {
    private JLabel labelGameBoard;
    private JLabel labelCard;
    private JLabel labelPlayer;
    private JLabel labelCurrentPlayer;
    private JLabel labelMenu;

    private JButton buttonAct;
    private JButton buttonRehearse;
    private JButton buttonMove;

    private JLayeredPane paneDeadwood;

    private ImageIcon iconGameBoard;

    private static final String DEADWOOD_TITLE = "Deadwood";
    private static final String GAME_BOARD_IMAGE = "board.jpg";
    private static final String CARD_IMAGE = "cards/01.png";
    private static final String DICE_IMAGE = "dice/r2.png";
    private static final String MENU_LABEL_TEXT = "MENU";
    private static final String ACT_BUTTON_TEXT = "ACT";
    private static final String REHEARSE_BUTTON_TEXT = "REHEARSE";
    private static final String MOVE_BUTTON_TEXT = "MOVE";

    // Things I have added
    private String name = "Name";
    private String playerName = "<html>" + name + "<br>" + "newline";
    private static DeadwoodFrame deadwoodFrame;



    public DeadwoodFrame() {
        super(DEADWOOD_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeLabels();
        initializeButtons();
        initializeDeadwoodPane();
    }

    private void initializeLabels() {
        setupGameBoardLabel();
        setupCardLabel();
        setupPlayerLabel();
        setupMenuLabel();
        setupCurrentPlayerLabel();
        // updateCurrentPlayer();
    }

    private void setupGameBoardLabel() {
        labelGameBoard = new JLabel();
        iconGameBoard = new ImageIcon(GAME_BOARD_IMAGE);
        labelGameBoard.setIcon(iconGameBoard);
        labelGameBoard.setBounds(0, 0, iconGameBoard.getIconWidth(), iconGameBoard.getIconHeight());
        setSize(iconGameBoard.getIconWidth() + 200, iconGameBoard.getIconHeight()); // Set the size of the GUI
    }

    private void setupCardLabel() {
        labelCard = new JLabel();
        ImageIcon cardIcon = new ImageIcon(CARD_IMAGE);
        labelCard.setIcon(cardIcon);
        labelCard.setBounds(20, 65, cardIcon.getIconWidth() + 2, cardIcon.getIconHeight());
        // labelCard.setOpaque(true);
        labelCard.setVisible(true);
    }

    private void setupPlayerLabel() {
        labelPlayer = new JLabel();
        ImageIcon playerDiceIcon = new ImageIcon(DICE_IMAGE);
        labelPlayer.setIcon(playerDiceIcon);
        labelPlayer.setBounds(114, 227, playerDiceIcon.getIconWidth(), playerDiceIcon.getIconHeight());
        labelPlayer.setBounds(114,227,46,46);
    }

    public void updateCurrentPlayer(Player currentPlayer) {
        name = currentPlayer.getName();
        System.out.printf("updateCurrentPlayer: %s\n", name);

        // playerName = name;





        labelCurrentPlayer.setText(name);
        System.out.printf("updateCurrentPlayer2: %s\n", labelCurrentPlayer.getText());
        labelCurrentPlayer.paintImmediately(labelCurrentPlayer.getVisibleRect());
        paneDeadwood.remove(labelCurrentPlayer);
        paneDeadwood.add(labelCurrentPlayer);
        // paneDeadwood.validate();
        // paneDeadwood.repaint();
        // labelCurrentPlayer.paintImmediately(labelCurrentPlayer.getVisibleRect());
        // deadwoodFrame.repaint();
        // ImageIcon playerDiceIcon = new ImageIcon(DICE_IMAGE);
        // labelPlayer.setIcon(playerDiceIcon);
        // labelPlayer.setBounds(114, 227, playerDiceIcon.getIconWidth(), playerDiceIcon.getIconHeight());
        // labelPlayer.setBounds(114,227,46,46);
        // labelCurrentPlayer.setBounds(iconGameBoard.getIconWidth() + 40, 200, 100, 20);
    }

    private void setupCurrentPlayerLabel() {
        labelCurrentPlayer = new JLabel(playerName);
        labelCurrentPlayer.setBounds(iconGameBoard.getIconWidth() + 40, 200, 150, 100);
    }

    private void setupMenuLabel() {
        labelMenu = new JLabel(MENU_LABEL_TEXT);
        labelMenu.setBounds(iconGameBoard.getIconWidth() + 40, 0, 100, 20);
    }

    private void initializeButtons() {
        setupActButton();
        setupRehearseButton();
        setupMoveButton();
    }

    private void setupActButton() {
        buttonAct = new JButton(ACT_BUTTON_TEXT);
        buttonAct.setBackground(Color.white);
        buttonAct.setBounds(iconGameBoard.getIconWidth() + 10, 30, 100, 20);
        buttonAct.addMouseListener(new ActButtonMouseListener());
    }

    private void setupRehearseButton() {
        buttonRehearse = new JButton(REHEARSE_BUTTON_TEXT);
        buttonRehearse.setBackground(Color.white);
        buttonRehearse.setBounds(iconGameBoard.getIconWidth() + 10, 60, 150, 20);
        buttonRehearse.addMouseListener(new RehearseButtonMouseListener());
    }

    private void setupMoveButton() {
        buttonMove = new JButton(MOVE_BUTTON_TEXT);
        buttonMove.setBackground(Color.gray);
        buttonMove.setBounds(iconGameBoard.getIconWidth() + 10, 90, 100, 20);
        buttonMove.addMouseListener(new MoveButtonMouseListener());
    }

    public void initializeDeadwoodPane() {
        paneDeadwood = getLayeredPane();
        paneDeadwood.add(labelGameBoard, new Integer(0)); // Add the board to the lowest layer
        paneDeadwood.add(labelCard, new Integer(1)); // Add the card to the lower layer
        paneDeadwood.add(labelPlayer, new Integer(3));
        paneDeadwood.add(labelCurrentPlayer, new Integer(3));
        paneDeadwood.add(labelMenu, new Integer(2));

        paneDeadwood.add(buttonAct, new Integer(2));
        paneDeadwood.add(buttonRehearse, new Integer(2));
        paneDeadwood.add(buttonMove, new Integer(2));
    }
    public static void main(String[] args) {
      DeadwoodFrame board = new DeadwoodFrame();
      board.setVisible(true);
    }

    public static void makeFrame() {
      DeadwoodFrame board = new DeadwoodFrame();
      deadwoodFrame = board;
      board.setVisible(true);
    }

    public static void updateBoard() {

    }
}
