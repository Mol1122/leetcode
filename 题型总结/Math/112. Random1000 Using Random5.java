/* Given a random generator random5(), the return value of random5() is 0 - 4 with equal probability. Use random5() to implement random1000() */

public class Solution {
  public int random1000() {
    while (true) {
      int num = 0;
      for (int i = 0; i < 5; i++) {
        num = num * 5 + RandomFive.random5();
      }
      if (num < 3000) {
        return num % 1000;
      }
    }
  }
}
