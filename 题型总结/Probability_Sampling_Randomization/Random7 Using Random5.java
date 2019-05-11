public class Solution {
  public int random7() {
      while (true) {
          int r = RandomFive.random5() * 5 + RandomFive.random5();
          if (r < 21) {
              return r % 7;
          }
      }
  }
}
