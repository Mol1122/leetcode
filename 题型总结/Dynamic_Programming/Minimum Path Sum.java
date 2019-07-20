/* Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.You can only move either down or right at any point in time.

Input: [

           [5, 1, 2, 4],

           [4, 1, 0, 1],

           [0, 3, 7, 6]

           ]    

Output: 14 */

public class Solution {
  public int miniSum(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    int n = grid.length;
    int m = grid[0].length;
    int[][] f = new int[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        f[i][j] = Integer.MAX_VALUE;
        if (i == 0 && j == 0) {
          f[i][j] = grid[i][j];
          continue;
        }
        if (i > 0) {
          f[i][j] = Math.min(f[i][j], f[i - 1][j] + grid[i][j]);
        }
        if (j > 0) {
          f[i][j] = Math.min(f[i][j], f[i][j - 1] + grid[i][j]);
        }
      }
    }
    return f[n - 1][m - 1];
  }
}
//time: O(n), space: O(n^2) can be optimized to O(n)