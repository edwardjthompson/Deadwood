
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

  public void printRole() {
    System.out.printf("[%d] (%s): \n\t%s\n", rank, name, line);
  }

  public String getName() {
    return name;
  }
}
