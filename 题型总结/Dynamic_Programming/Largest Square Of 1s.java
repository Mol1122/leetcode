/* Determine the largest square of 1s in a binary matrix (a binary matrix only contains 0 and 1), return the length of the largest square.

Assumptions

The given matrix is not null and guaranteed to be of size N * N, N >= 0
Examples

{ {0, 0, 0, 0},

  {1, 1, 1, 1},

  {0, 1, 1, 1},

  {1, 0, 1, 1}}

the largest square of 1s has length of 2

  */
public class Solution {
  public int largest(int[][] matrix) {
      if (matrix == null || matrix.length == 0) {
          return 0;
      }
      int n = matrix.length;
      int m = matrix[0].length;
      int[][] f = new int[n][m];
    
      int max = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (i == 0 || j == 0) {
                f[i][j] = matrix[i][j] == 1 ? 1 : 0;
            } else {
                if (matrix[i][j] == 1) {
                    f[i][j] = Math.min(f[i - 1][j - 1], Math.min(f[i - 1][j], f[i][j - 1])) + 1;
                }
            }
            max = Math.max(max, f[i][j]);
        }
      }
       return max;
  }
}
//f[i][j] = max square edge length I can get if i choose matricx[i][j] as right bootom corner
/*  
101
111
011
*/

//time: O(n*m), space: O(n*m)
