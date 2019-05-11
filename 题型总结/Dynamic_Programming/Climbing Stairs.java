public class Solution {
  public int stairs(int n) {
      if (n < 0) {
          return -1;
      }
      int[] f = new int[n + 1];
      f[0] = 1;
    
      for (int i = 1; i <= n; i++) {
          f[i] = f[i - 1];
          if (i >= 2) {
              f[i] += f[i - 2];
          }
      }
      return f[n];
  }
}
//f[i] = # ways to climb stair i
//time: O(n), space: O(n)
