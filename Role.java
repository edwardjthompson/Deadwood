
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
    takenBy.leaveRole();
    takenBy = null;
  }

  public String getName() {
    return name;
  }

  public boolean isMain() {
    return isMain;
  }

  public void printRole() {
    // if(isMain) {
    //   System.out.print("Main ");
    // }
    if(takenBy != null) {
      System.out.printf("Taken by: %s ", takenBy.getName());
    }
    System.out.printf("(Rank: %d) (%s): %s\n", rank, name, line);
  }

  public Player getPlayer(){
    return takenBy;
  }

  public int getRank() {
    return rank;
  }
}
