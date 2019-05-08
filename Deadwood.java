
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Deadwood {
  private int currentDay;
  private int numDays;
  private Board board;
  private int numPlayers;
  private ArrayList<Player> listOfPlayer;

  public static final String PATHBOARD = "boardInfo.txt";
  public static final String PATHSCENE = "sceneInfo.txt";
  public static final int ROOMNUM = 12;

  public static void main(String[] args) {
    Deadwood deadwood = new Deadwood();
    deadwood.initializeGame();
  }

  private void initializeGame() {
    board = new Board(ROOMNUM);
    createLocations();
    createScenes();

    board.printBoard();
  }

  private void startGame() {

  }

  private void startDay() {

  }

  private void createLocations() {
    File file;
    Scanner s;
    String line = "";
    Location current = null;

    try {
      file = new File(PATHBOARD);
      s = new Scanner(file);

      while(s.hasNext()) {
        line = s.nextLine();

        if(line.equals("A-ROOM")) {
          line = s.nextLine();
          ActingLocation temp = new ActingLocation(line);
          line = s.nextLine();
          temp.setShots(Integer.parseInt(line));
          current = temp;
        }
        else if(line.equals("T-ROOM")) {
          line = s.nextLine();
          current = new Location(line);
        }
        else if(line.equals("C-ROOM")) {
          line = s.nextLine();
          current = new CastingOffice(line);
        }
        else if(line.equals("ROLE")) {
          String name = s.nextLine();
          String quote = s.nextLine();
          int rank = Integer.parseInt(s.nextLine());
          Role role = new Role(rank, name, quote, false);
          ActingLocation temp = (ActingLocation)current;
          temp.addRole(role);
        }
        else {
          board.addLocation(current);
        }
      }
      s.close();
    }
    catch(FileNotFoundException e) {
      e.printStackTrace();
    }
    catch(NumberFormatException e) {
      e.printStackTrace();
    }
  }

  private void createScenes() {
    File file;
    Scanner s;
    String line = "";
    String title = "";
    int br = 0;
    Scene currentScene = null;
    Role currentRole = null;

    try {
      file = new File(PATHSCENE);
      s = new Scanner(file);

      while(s.hasNext()) {
        line = s.nextLine();

        if(line.equals("SCENE")) {
          title = s.nextLine();
          line = s.nextLine();
          br = Integer.parseInt(s.nextLine());
          currentScene = new Scene(title, line, br);
        }
        else if(line.equals("ROLE")) {
          title = s.nextLine();
          line = s.nextLine();
          br = Integer.parseInt(s.nextLine());
          currentRole = new Role(br, title, line, true);
          currentScene.addRole(currentRole);
        }
        else {
          board.addScene(currentScene);
        }
      }
      s.close();
    }
    catch(FileNotFoundException e) {
      e.printStackTrace();
    }
    catch(NumberFormatException e) {
      e.printStackTrace();
    }
  }

  private void createPlayers() {

  }

  private void endGame() {

  }
}
