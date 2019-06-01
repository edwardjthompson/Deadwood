import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;

public class DeadwoodController {
  private Player currentplayer;
  private Deadwood deadwood;
  private DeadwoodFrame deadwoodFrame;
  private String input;
  private int intInput;
  private boolean inputSet;

  public static void main(String[] args) {
    DeadwoodController deadwoodController = new DeadwoodController();



    // Getting numPlayer from main args
    int num = 0;
    try {
      if (args.length > 0) {
        num = Integer.parseInt(args[0]);
      }
    }
    catch(NumberFormatException e) {
      // Sets to 0 and calls setPlayerCount to set the value
      num = 0;
    }

    deadwoodController.initializeController(num, deadwoodController);
    // DeadwoodFrame deadwoodFrame = new DeadwoodFrame();
    // this.deadwoodFrame = deadwoodFrame;
    // deadwoodFrame.makeFrame();
    //
    // deadwood = new Deadwood(num, deadwoodController);


    // initializeGame
  }

  private void initializeController(int num, DeadwoodController deadwoodController) {
    deadwoodFrame = DeadwoodFrame.makeFrame(this);
    deadwood = new Deadwood(num, deadwoodController);
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
      System.out.println(roleChoices.get(i));
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

//    deadwoodFrame.labelCurrentPlayer.setText(p.getName());
//    System.out.print("Repaint\n");
    deadwoodFrame.labelCurrentPlayer.setText(playerInfo);


//    ArrayList<String> turnChoices = p.getTurnChoices();
//
//    int size = turnChoices.size();
//    System.out.println("Size of array: " + size);
//
//    for (String s : turnChoices) {
//      System.out.println(s);
//    }
    // deadwoodFrame.updateCurrentPlayer(p);
    // deadwoodFrame.removeAll();
    // deadwoodFrame.revalidate();
    // deadwoodFrame.repaint();
  }

  public void buttonInput(String s){
    System.out.println("buttonInput: " + s);
    input = s;
    inputSet = true;
  }

  public void buttonInput(int n){
    System.out.println("buttonInput: " + n);
    intInput = n;
    inputSet = true;
  }

  public String playerOptions(ArrayList<String> turnChoices) {
    int size = turnChoices.size();
    System.out.println("number of choices: " + size);
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
        default :
          System.out.println("No buttons for " + s);
      }
      yAxisOfButton += 50;
    }

    while(!inputSet) {
      System.out.print("");
    }
    return input;
  }

  // initializeGame

  // update currentplayer

  // update mainTurnOptions

  // updateBoard

  //


}
