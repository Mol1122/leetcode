/* Determine the largest square surrounded by 1s in a binary matrix (a binary matrix only contains 0 and 1), 
return the length of the largest square.

Assumptions

The given matrix is guaranteed to be of size M * N, where M, N >= 0

Examples

{{1, 0, 1, 1, 1},

 {1, 1, 1, 1, 1},

 {1, 1, 0, 1, 0},

 {1, 1, 1, 1, 1},

 {1, 1, 1, 0, 0}}

The largest square surrounded by 1s has length of 3. */

public class Solution {
  public int largestSquareSurroundedByOne(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }
    int[][] left = getLeft(matrix);
    int[][] up = getUp(matrix);
    int max = matrix[0][0];
        
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 1) {
          for (int length = 1; length <= Math.min(left[i][j], up[i][j]); length++) {
            if (left[i - length + 1][j] < length) {
              continue;
            }
            if (up[i][j - length + 1] < length) {
              continue;
            }
            max = Math.max(max, length);
          }
        }
      }
    }
    return max;
  }

  private int[][] getLeft(int[][] matrix) {
    int[][] left = new int[matrix.length][matrix[0].length];

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 1) {
          left[i][j] = getNumber(left, i, j - 1) + 1;
        }
      }  
    }
    return left;
  }

  private int[][] getUp(int[][] matrix) {
    int[][] up = new int[matrix.length][matrix[0].length];

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 1) {
          up[i][j] = getNumber(up, i - 1, j) + 1;
        }          
      }
    }
    return up;   
  }

  private int getNumber(int[][] array, int x, int y) {
    if (x < 0 || x >= array.length || y < 0 || y >= array[0].length) {
      return 0;
    }
    return array[x][y];
  }
}
//time: O(n^3), space: O(n^2)