
public class Role {
  private int rank;
  private String name;
  private String line;
  private boolean isMain;
  private Player takenBy;

  public Role(int rank, String name, String line, boolean isMain) {
    this.rank = rank;
    this.name = name;
    this.line = line;
    this.isMain = isMain;
  }

  //Returns true if it was successful and false if it failed
  public boolean take(Player player) {
    if(takenBy == null) {
      takenBy = player;
      return true;
    }
    else {
      return false;
    }
  }

  //May not ever be used
  public void leave() {
    takenBy = null;
  }

  public void printRole() {
    System.out.println(name);
    if(isMain) {
      System.out.print("A Staring Role ");
    }
    if(takenBy != null) {
      System.out.printf("taken by: %s", takenBy.getName());
    }
    System.out.println();
    System.out.println(line);
    System.out.println(rank);
    System.out.println();
  }

  public Player getPlayer(){
    return takenBy;
  }
}
