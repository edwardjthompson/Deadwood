
import java.util.ArrayList;

public class Deadwood {
  private int currentDay;
  private int numDays;
  private Board board;
  private int numPlayers;
  private ArrayList<Player> listOfPlayer;

  //For the text files that contain the information on the game
  public static final String PATHBOARD = "boardInfo.txt";
  public static final String PATHSCENE = "sceneInfo.txt";

  //May not be neccecary, but I felt like it might be usful
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

  /* I basically moved these functions down into board, you might be able to merge them if you want */
  //Moved into Board
  private void createLocations() {
    board.setUpLocations(PATHBOARD);
  }
  //Moved into Board
  private void createScenes() {
    board.setUpScenes(PATHSCENE);
  }

  private void createPlayers() {

  }

  private void endGame() {

  }
}
