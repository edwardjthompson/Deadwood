import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;

public class DeadwoodController {
  private Player currentplayer;
  private DeadwoodModel deadwood;
  private DeadwoodFrame deadwoodFrame;
  private String input;
  private int intInput;
  private boolean inputSet;

  public void initializeController(DeadwoodController deadwoodController) {
    int playerCount = setPlayerCount();
    deadwoodFrame = DeadwoodFrame.makeFrame(this);
    deadwood = new DeadwoodModel(playerCount, deadwoodController);
    deadwood.runGame();
    deadwoodFrame.setVisible(false);
  }

  private int setPlayerCount() {
    String[] options = {"  2  ", "  3  "};

    int num = 2 + JOptionPane.showOptionDialog(null, "Choose the number of" +
    " players:", "Player Selection", JOptionPane.DEFAULT_OPTION,
    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

    return num;
  }

  public void endDay (int currentDay) {
    String message = "End of day ";
    message = message.concat(Integer.toString(currentDay));
    JOptionPane.showMessageDialog(null, message);
  }

  public void endGame(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

  public void roleTaken(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

  public int move(Player p) {
    int retVal = -2;
    int yAxisOfButton = 130;
    inputSet = false;
    deadwoodFrame.removeButtons();
    Location pLocation = p.getLocation();
    int size = pLocation.adjacentLocationList.size();
    deadwoodFrame.buttonLocations.clear();

    for(int i = 0; i < size; i++) {
      String name = pLocation.adjacentLocationList.get(i).getName();
      JButton button = new JButton(name);
      button.setBackground(Color.white);
      button.addMouseListener(new AdjacentLocationButtonMouseListener(deadwoodFrame, this, i));
      deadwoodFrame.paneDeadwood.add(button, new Integer(2));
      deadwoodFrame.positionButton(button, 10, yAxisOfButton, 220, 50);
      yAxisOfButton += 50;
      deadwoodFrame.buttonLocations.add(button);
    }
    while(!inputSet) {
      System.out.print("");
    }
    retVal = intInput;
    return retVal;
  }

  public int role(Player p, ArrayList<String> roleChoices) {
    int retVal = -2;
    int yAxisOfButton = 130;
    inputSet = false;
    deadwoodFrame.removeButtons();
    deadwoodFrame.buttonRoles.clear();
    int numOfChoices = roleChoices.size();
    int i;
    for (i = 0; i < numOfChoices; i++) {
      String name = roleChoices.get(i);
      //System.out.println(roleChoices.get(i));
      if (name.charAt(0) != '0') {
        JButton button = new JButton(name);
        button.setBackground(Color.white);
        button.addMouseListener(new RoleButtonMouseListener(deadwoodFrame, this, i));
        deadwoodFrame.paneDeadwood.add(button, new Integer(2));
        deadwoodFrame.positionButton(button, 10, yAxisOfButton, 220, 50);
        yAxisOfButton += 50;
        deadwoodFrame.buttonRoles.add(button);
      }
    }
    // Take no role option
    JButton button = new JButton("TAKE NO ROLE");
    button.setBackground(Color.white);
    button.addMouseListener(new RoleButtonMouseListener(deadwoodFrame, this, -1));
    deadwoodFrame.paneDeadwood.add(button, new Integer(2));
    deadwoodFrame.positionButton(button, 10, yAxisOfButton, 220, 50);
    deadwoodFrame.buttonRoles.add(button);

    while(!inputSet) {
      System.out.print("");
    }
    retVal = intInput;

    return retVal;
  }

  public int upgradeRank(ArrayList<String> listOptions, int playerRank) {
    String[] options = new String[listOptions.size()];
    options = listOptions.toArray(options);

    int size = listOptions.size();

    int num = JOptionPane.showOptionDialog(null, "Which rank would you" +
    " like to choose?", "Upgrade Rank", JOptionPane.DEFAULT_OPTION,
    JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

    if (num == size-1) {
      // Cancel
      num = -1;
    }
    else {
      num = num + 1 + playerRank;
    }

    return num;
  }

  public String upgradePayment() {
    String[] options = {"Dollars", "Credits"};

    int num = JOptionPane.showOptionDialog(null, "Would you like to use" +
    " Dollars or Credits?", "Upgrade Payment Selection",
    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
    options, options[0]);

    if (num == 0) return "d";
    if (num == 1) return "c";

    return "error";
  }

  public void repaintFrame(Player p) {
    String name = p.getName();
    String dollars = Integer.toString(p.getDollars());
    String credits = Integer.toString(p.getCredits());
    String rank = Integer.toString(p.getRank());
    String rehearsals = Integer.toString(p.getNumRehearsals());

    String location;
    if (p.getLocation() == null) {
      location = "No current location";
    }
    else {
      location = p.getLocation().getName();
    }

    String role;
    if (p.getRole() == null) {
      role = "No current role";
    }
    else {
      role = p.getRole().getName();
    }

    String playerInfo = "<html>" + "<font color = " + name + ">" +
            "Current Player: " + name + "<br>" +
            "  Dollars: " + dollars + "<br>" +
            "\tCredits: " + credits + "<br>" +
            "\tRank: " + rank + "<br>" +
            "\tRehearsals: " + rehearsals + "<br>" +
            "\tLocation: " + location + "<br>" +
            "\tRole: " + role;

    deadwoodFrame.labelCurrentPlayer.setText(playerInfo);
  }

  public void repaintFrame() {
    ArrayList<Image> images = deadwood.getImages();
    Image current;
    deadwoodFrame.refreshScreen();
    //System.out.println("Are you repainting");
    for(int i = 0; i < images.size(); i++) {
      current = images.get(i);
      if(current != null) {
        deadwoodFrame.setupImageLabel(current.getPath(), current.getX(), current.getY(), current.isPriority());
      }
    }
  }

  public void buttonInput(String s){
    //System.out.println("buttonInput: " + s);
    input = s;
    inputSet = true;
  }

  public void buttonInput(int n){
    //System.out.println("buttonInput: " + n);
    intInput = n;
    inputSet = true;
  }

  public String playerOptions(ArrayList<String> turnChoices) {
    int size = turnChoices.size();
    //System.out.println("number of choices: " + size);
    int yAxisOfButton = 130;
    inputSet = false;

    deadwoodFrame.removeButtons();

    for (String s : turnChoices) {
      switch(s) {
        case "m" :
          deadwoodFrame.positionButton(deadwoodFrame.buttonMove,10, yAxisOfButton, 220, 50);
          break;
        case "a" :
          deadwoodFrame.positionButton(deadwoodFrame.buttonAct,10, yAxisOfButton, 220, 50);
          break;
        case "r" :
          deadwoodFrame.positionButton(deadwoodFrame.buttonRehearse,10, yAxisOfButton, 220, 50);
          break;
        case "u" :
          deadwoodFrame.positionButton(deadwoodFrame.buttonUpgrade,10, yAxisOfButton, 220, 50);
          break;
        case "s" :
          deadwoodFrame.positionButton(deadwoodFrame.buttonSkip,10, yAxisOfButton, 220, 50);
          break;
      }
      yAxisOfButton += 50;
    }

    while(!inputSet) {
      System.out.print("");
    }
    return input;
  }
}
