public class Solution {
  public int diffBits(int a, int b) {
      a ^= b;
      int count = 0;
      while (a != 0) {
          count += (a & 1);
          a = (a >>> 1); //难点，因为有可能是负数
      }
      return count;
  }
}
//time: O(#bits), space: O(1)
