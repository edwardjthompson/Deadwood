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

  //May not be necessary, but I felt like it might be useful
  public static final int ROOMNUM = 12;

  public static void main(String[] args) {


    // Creating instance of objects
    Deadwood deadwood = new Deadwood();
    deadwood.initializeGame();



  }

  private void initializeGame() {
    this.currentDay = 1;
    this.numDays = 4;
    this.board = new Board(ROOMNUM);
    board.setUpLocations(PATHBOARD);
    board.setUpScenes(PATHSCENE);
    setPlayerCount();
    createPlayers();
//    runGame();
  }

  private void runGame() {
    System.out.println("Start game");
    while (board.getNumScenesRemaining() > 1) {
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
    for (int i = 0; i < numPlayers; i++) {
      Player p = new Player(playerNames[i], board.getLocation("Trailers"));
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
    System.out.println("End Game");
  }

  private void runDay() {
    System.out.println("runDay");
    for (Player currentPlayer : listOfPlayers) {
      takeTurn(currentPlayer);
    }
  }

  private void setPlayerCount() {
    System.out.println("Statically setting numPlayers to 3 in setPlayerCount()");
//    numPlayers = 3;
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
    input.close();
  }

  private void takeTurn(Player currentPlayer) {
    currentPlayer.printPlayer();
    Scanner input = new Scanner(System.in);
    boolean optionChosen = false;
    int choice = 0;


    while (!optionChosen) {
    System.out.print("Choose an option [1] [2] [3]: ");

    if(input.hasNextInt()) {
      choice = input.nextInt();

      if((choice < 1) || (choice > 3)) {
        choice = 0;
        System.out.println("Invalid choice");
      }
      else optionChosen = true;
    }
    else input.next();
    }
    System.out.printf("Choice = %d\n\n", choice);
    input.close();
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
