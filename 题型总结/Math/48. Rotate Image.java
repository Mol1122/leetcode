public class Solution {
  public void rotate(int[][] matrix) {
      if (matrix == null || matrix.length == 0) {
          return;
      }
      int n = matrix.length;
      for (int r = 0; r < n / 2; r++) {
          for (int c = 0; c < (n + 1) / 2; c++) {
              int temp = matrix[r][c];
              matrix[r][c] = matrix[n - 1 - c][r];
              matrix[n - 1 - c][r] = matrix[n - 1 - r][n - 1 - c];
              matrix[n - 1 - r][n - 1 - c] = matrix[c][n - 1 - r];
              matrix[c][n - 1 - r] = temp;
          }
      }
  }
}

//time: O(n^2), space: O(1)
//难点： inner for loop的终止条件,否则不能全部遍历完所有的点
