
public class Role {
  private int rank;
  private String name;
  private String line;
  private boolean isMain;
  private Player takenBy;

  private int x;
  private int y;

  public Role(int rank, String name, String line, boolean isMain, int x, int y) {
    this.rank = rank;
    this.name = name;
    this.line = line;
    this.isMain = isMain;
    this.x = x;
    this.y = y;
  }

  //Returns true if it was successful and false if it failed
  public boolean take(Player player) {
    if(takenBy == null && player.getRank() >= rank) {
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

  public Image printRole() {
    Image image;
    if(takenBy != null) {
      image = new Image(takenBy.getName(), x, y);
    }
    return image;
  }

  public Player getPlayer(){
    return takenBy;
  }

  public int getRank() {
    return rank;
  }
}
