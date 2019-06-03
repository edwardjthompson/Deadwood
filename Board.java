
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Board {
  private ArrayList<Scene> listOfScenes;
  private Location[] locations;
  private Random r;

  public Board(int size) {
    locations = new Location[size];
    listOfScenes = new ArrayList<Scene>();
    r = new Random();
  }

  public int getNumScenesRemaining() {
    int count = 0;
    for(int i = 0; i < locations.length; i++) {
      if(!locations[i].hasSceneFinished()) count++;
    }
    return count;
  }

  //Note I changed this to take a list of players, just was useful
  public void endDay(ArrayList<Player> players) {
    System.out.println("End Day");
    for(int i = 0; i < locations.length; i++) {
      //Take them out Acting Locations
      if(locations[i] instanceof ActingLocation) {
        ActingLocation temp = (ActingLocation) locations[i];
        dealScene(temp);
      }

      else {
        //Add them to Trailer
        if(!(locations[i] instanceof CastingOffice)) {
          for(int j = 0; j < players.size(); j++) {
            players.get(j).goToLocation(locations[i]);
          }
        }
      }

    }
  }

  public ArrayList<Image> printBoard() {
    ArrayList<Image> images = new ArrayList<Image>();
    for(int i = 0; i < locations.length; i++) {
      images.addAll(locations[i].printLocation());
    }
  }

  public void setUpLocations(String path) {
    File file;
    Scanner s;
    String line = "";
    String name;
    String quote;
    int rank;
    int x;
    int y;
    Location current = null;
    Role currentRole = null;

    try {
      //Set up the scanner with the file given
      file = new File(path);
      s = new Scanner(file);

      while(s.hasNext()) {
        //While the file still has content
        line = s.nextLine();

        //If it finds a header in the file, it will respond to it
        if(line.equals("A-ROOM")) {
          //Sets up an Acting Location
          line = s.nextLine();
          x = Integer.parseInt(s.nextLine());
          y = Integer.parseInt(s.nextLine());
          int sx = Integer.parseInt(s.nextLine());
          int sy = Integer.parseInt(s.nextLine());
          ActingLocation temp = new ActingLocation(line, x, y, sx, sy);
          dealScene(temp);
          line = s.nextLine();
          String line2 = s.nextLine();
          x = Integer.parseInt(s.nextLine());
          y = Integer.parseInt(s.nextLine());
          temp.setShots(Integer.parseInt(line), line2, x, y);
          current = temp;
        }
        else if(line.equals("T-ROOM")) {
          //Sets up a boring old Trailer Location
          line = s.nextLine();
          x = Integer.parseInt(s.nextLine());
          y = Integer.parseInt(s.nextLine());
          current = new Location(line, x, y);
        }
        else if(line.equals("C-ROOM")) {
          //Sets up a Casting Office
          line = s.nextLine();
          x = Integer.parseInt(s.nextLine());
          y = Integer.parseInt(s.nextLine());
          current = new CastingOffice(line, x, y);
        }
        else if(line.equals("ROLE")) {
          //Sets up a role then gives it to whatever room had been recently set
          name = s.nextLine();
          quote = s.nextLine();
          rank = Integer.parseInt(s.nextLine());
          x = Integer.parseInt(s.nextLine());
          y = Integer.parseInt(s.nextLine());
          currentRole = new Role(rank, name, quote, false, x, y);
          ActingLocation temp = (ActingLocation)current;
          temp.addRole(currentRole);
        }
        else {
          //Adds the location to the array
          //Should be done after roles are looped through
          addLocation(current);
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

  public void setUpBoardConnections(String path) {
    File file;
    Scanner s;
    String line = "";
    String name = "";
    Location current = null;

    try {
      //Set up the scanner with the file given
      file = new File(path);
      s = new Scanner(file);

      while(s.hasNext()) {
        //While the file still has content
        line = s.nextLine();

        //If it finds a header in the file, it will respond to it
        if(line.equals("ROOM")) {
          //Sets up current location
          name = s.nextLine();
          for(int i = 0; i < locations.length; i++) {
            if(locations[i].getName().equals(name)) {
              current = locations[i];
            }
          }
        }
        else {
          //Adds location to current
          for(int i = 0; i < locations.length; i++) {
            if(locations[i].getName().equals(line)) {
              current.addAdjacent(locations[i]);
            }
          }
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

  public void setUpScenes(String path) {
    File file;
    Scanner s;
    String line = "";
    String title = "";
    int br = 0;
    Scene currentScene = null;
    Role currentRole = null;

    try {
      //Set up the scanner with the file given
      file = new File(path);
      s = new Scanner(file);

      while(s.hasNext()) {
        //While file still has content
        line = s.nextLine();

        //If it finds a header it responds to it
        if(line.equals("SCENE")) {
          //Sets up a new scene
          title = s.nextLine();
          line = s.nextLine();
          br = Integer.parseInt(s.nextLine());
          currentScene = new Scene(title, line, br);
        }
        else if(line.equals("ROLE")) {
          //Sets up a role and adds it to the current scene
          title = s.nextLine();
          line = s.nextLine();
          br = Integer.parseInt(s.nextLine());
          x = Integer.parseInt(s.nextLine());
          y = Integer.parseInt(s.nextLine());
          currentRole = new Role(br, title, line, true, x, y);
          currentScene.addRole(currentRole);
        }
        else {
          //Adds the current scene to the "deck"
          //Should be done after all the roles associated with it are processed
          listOfScenes.add(currentScene);
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

  //Just to setting up Locations more tame
  //Adds location into the next available slot in the array
  private void addLocation(Location room) {
    int count = 0;
    while(count < locations.length) {
      if(locations[count] == null) {
        locations[count] = room;
        break;
      }
      count++;
    }
  }

  public Location getLocation(String name) {
    int len = locations.length;
    for (int i = 0; i < len; i++) {

      if (locations[i].getName().equals(name)) {
        return locations[i];
      }
    }
    return null;
  }

  // Can this be private?
  private void dealScene(ActingLocation location) {
    int index = r.nextInt(listOfScenes.size());
    location.setScene(listOfScenes.get(index));
    listOfScenes.remove(listOfScenes.get(index));
  }
}
