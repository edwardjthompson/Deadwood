import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Deadwood {
  private int currentDay;
  private int numDays;
  private Board board;
  private int numPlayers;
  private ArrayList<Player> listOfPlayers;

  private final int MIN_PLAYERS = 2;
  private final int MAX_PLAYERS = 3;

  //For the text files that contain the information on the game
  public static final String PATHBOARD = "boardInfo.txt";
  public static final String PATHSCENE = "sceneInfo.txt";
  public static final String PATHCONNECTIONS = "boardConnections.txt";

  //May not be necessary, but I felt like it might be useful
  public static final int ROOMNUM = 12;

  public static void main(String[] args) {


    // Creating instance of objects
    Deadwood deadwood = new Deadwood();
    deadwood.initializeGame();
    deadwood.runGame();

    // deadwood.testGame();



  }

  private void testGame() {

  }

  private void initializeGame() {
    this.currentDay = 1;
    this.numDays = 4;
    this.board = new Board(ROOMNUM);
    board.setUpScenes(PATHSCENE);
    board.setUpLocations(PATHBOARD);
    board.setUpBoardConnections(PATHCONNECTIONS);
    setPlayerCount();
    createPlayers();
//    runGame();
  }

  private void runGame() {
    System.out.println("Start game");
    while (currentDay <= numDays) {
      runDay();
    }
    endGame();
  }

  private void startDay() {
    Location trailers = board.getLocation("Trailers");
    for (Player p : listOfPlayers) {
      p.setCurrentLocation(trailers);
    }
  }

  private void createPlayers() {
    this.listOfPlayers = new ArrayList<Player>();
    String playerNames[] = {"red", "green", "blue"};
    String nameColor[] = {"\u001B[31m", "\u001B[32m", "\u001B[34m"};
    for (int i = 0; i < numPlayers; i++) {
      Player p = new Player(playerNames[i], board.getLocation("Trailers"), nameColor[i]);
      listOfPlayers.add(p);
    }
  }

  private void printListOfPlayers() {
    for (Player p : listOfPlayers) {
      p.printPlayer();
      p.takeTurn();
    }
  }

  private void endGame() {
    System.out.println("End Game\n");
    int highScore = 0;
    int playerScore;
    Player winner = null;
    String playerName;
    for (Player p : listOfPlayers) {
      playerScore = p.getScore();
      playerName = p.getName();
      System.out.printf("%s has a score of %d\n", playerName, playerScore);
      if (winner == null) {
        winner = p;
        highScore = playerScore;
      }
      else if (playerScore > highScore) {
        winner = p;
        highScore = playerScore;
      }
    }
    String winnerName = winner.getName();
    System.out.printf("\nThe winner is %s with score of %d\n", winnerName, highScore);
  }

  private void runDay() {
    System.out.println("runDay");
    int playerNum = 0;
    while (board.getNumScenesRemaining() > 1) {
      System.out.print("******************************************\n");
      board.printBoard();
      listOfPlayers.get(playerNum).takeTurn();
      playerNum++;
      if (playerNum >= numPlayers) {
        playerNum = 0;
      }
    }
    if (currentDay < numDays) {
      board.endDay(listOfPlayers);
    }
    currentDay++;
  }

  private void setPlayerCount() {
    numPlayers = 3;
    System.out.printf("\nPlayer count set for testing: %d\n", numPlayers);
    // Scanner used for initial Setup
    Scanner input = new Scanner(System.in);
    // Loops until user inputs a number between MIN_PLAYERS and MAX_PLAYERS
    while(numPlayers == 0) {
      System.out.printf("Choose the Number of players (%d-%d): ", MIN_PLAYERS, MAX_PLAYERS);

      if(input.hasNextInt()) {
        numPlayers = input.nextInt();

        if((numPlayers < MIN_PLAYERS) || (numPlayers > MAX_PLAYERS)) {
          numPlayers = 0;
          System.out.println("Invalid player count");
        }
      }
      else input.next();
    }
    System.out.printf("Players = %d\n\n", numPlayers);
  }

  private Board getBoard() {
    return board;
  }

  public ArrayList<Player> getListOfPlayers() {
    return listOfPlayers;
  }

  public int getCurrentDay() {
    return currentDay;
  }

  public int getNumDays() {
    return numDays;
  }
}
