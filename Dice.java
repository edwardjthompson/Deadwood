import java.util.Random;

public class Dice {
  public int[] roll(int numOfDice) {
    int max = 6;
    int min = 1;
    int[] temp = new int[numOfDice];

    for (int i = 0; i < numOfDice; i++) {
      Random rand = new Random();
      temp[i] = rand.nextInt((max - min) +1) + min;
    }
    return temp;
  }
}
