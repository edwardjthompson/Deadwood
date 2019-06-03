

public class Image {
  private String path;
  private int x;
  private int y;

  public Image(String path, int x, int y) {
    this.path = path;
    this.x = x;
    this.y = y;
  }

  public String getPath() {
    return path;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }
}
