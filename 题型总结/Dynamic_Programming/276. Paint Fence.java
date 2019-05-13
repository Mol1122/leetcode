public class Solution {
  public int numWays(int n, int k) {
      int[] f = {0, k, k*k, 0};
     
      if (n <= 2) {
          return f[n];
      }
      if (k == 1) {
          return 0;
      }
      for (int i = 2; i < n; i++) { //在index = i的时候其实已经计算到index = i + 1
          f[3] = f[2] * (k - 1) + f[1] * (k - 1);
          f[1] = f[2];
          f[2] = f[3];
      }
      return f[3];
  }
}
/* (1) 如果和前一个保持不同，很显然

num_ways_diff(i) = num_ways(i - 1) * (k - 1) // (k - 1) means all colors but the one painted on the (i - 1)th post
 

(2) 如果和前一个保持相同，由于题目限制不能连续三个相同，那么只有第(i - 1)和(i - 2)不同时，我们才能让第i个和第(i - 1)个相同。

num_ways_same(i) = num_ways_diff(i - 1) * 1 // 1 means only the color that is painted on the (i - 1)th post is allowed
 

将（1）和（2）加和整理，我们得到

num_ways(i) = num_ways_diff(i) + num_ways_same(i)
            = num_ways(i - 1) * (k - 1) + num_ways_diff(i - 1) * 1
            = num_ways(i - 1) * (k - 1) + num_ways(i - 2) * (k - 1) */
//time: O(n), space: O(1)