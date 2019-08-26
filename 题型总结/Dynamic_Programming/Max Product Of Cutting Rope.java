/* Given a rope with positive integer-length n, how to cut the rope into m integer-length parts 
with length p[0], p[1], ...,p[m-1], in order to get the maximal product of p[0]*p[1]* ... *p[m-1]? 
m is determined by you and must be greater than 0 (at least one cut must be made). Return the max 
product you can have.

Assumptions

n >= 2
Examples

n = 12, the max product is 3 * 3 * 3 * 3 = 81(cut the rope into 4 pieces with length of each is 3). */

public class Solution {
  public int maxProduct(int n) {
      //方法一：左大段，右大段
      //time: O(n^2), space: O(n)
      int[] f = new int[n + 1];
      f[0] = 0;
      f[1] = 0;
    
      for (int i = 2; i <= n; i++) {
          int currMax = 0;
          for (int j = 1; j <= i / 2; j++) {
              currMax = Math.max(currMax, 
                 Math.max(j, f[j]) * Math.max(i - j, f[i - j]));
          }
          f[i] = currMax;
      }
      return f[n];


    //方法二：左大段，右小段
    //time: O(n^2), space: O(n)
      int[] f = new int[n + 1];
      f[0] = 0;
      f[1] = 0;
      
      for (int i = 2; i <= n; i++) {
          int currMax = 0;
          for (int j = 1; j < i; j++) {
              currMax = Math.max(currMax, Math.max(j, f[j]) * (i - j));
          }
          f[i] = currMax;
      }
      return f[n];
  }
}
// --|- f[3] = f[2] * 1
// -|-- f[3] = f[1] * 2
// ___ f[3] = 3
//