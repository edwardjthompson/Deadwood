
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Board {
  private ArrayList<Scene> listOfScenes;
  private Location[] locations;

  public Board(int size) {
    locations = new Location[size];
    listOfScenes = new ArrayList<Scene>();
  }

  public int getNumScenesRemaining() {
    int count = 0;
    for(int i = 0; i < locations.length; i++) {
      if(!locations[i].hasSceneFinished()) count++;
    }
    return count;
  }

  public void endDay() {

  }

  public void printBoard() {
    for(int i = 0; i < locations.length; i++) {
      locations[i].printLocation();
    }
  }

  public void setUpLocations(String path) {
    File file;
    Scanner s;
    String line = "";
    String name;
    String quote;
    int rank;
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
          ActingLocation temp = new ActingLocation(line);
          line = s.nextLine();
          temp.setShots(Integer.parseInt(line));
          current = temp;
        }
        else if(line.equals("T-ROOM")) {
          //Sets up a boring old Trailer Location
          line = s.nextLine();
          current = new Location(line);
        }
        else if(line.equals("C-ROOM")) {
          //Sets up a Casting Office
          line = s.nextLine();
          current = new CastingOffice(line);
        }
        else if(line.equals("ROLE")) {
          //Sets up a role then gives it to whatever room had been recently set
          name = s.nextLine();
          quote = s.nextLine();
          rank = Integer.parseInt(s.nextLine());
          currentRole = new Role(rank, name, quote, false);
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
          currentRole = new Role(br, title, line, true);
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
  public void addLocation(Location room) {
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

      if (locations[i].getName() == name) {
        return locations[i];
      }
    }
    return null;
  }
}
