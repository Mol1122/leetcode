/* Given an matrix of integers, each row is sorted in ascending order from left to right, each column is also sorted in ascending 
order from top to bottom.

Determine how many negative numbers in the matrix.

Assumptions:

The given matrix is not null.
Examples:

{ {-5, -3, 0, 0, 1},

  {-3, -2, 1, 1, 3}

  {-2, 0,  3, 5, 6} }

The number of negative elements in the matrix is 5. */

public class Solution {
  public int negNumber(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }
    int i = 0, j = matrix[0].length - 1;
    int count = 0;

    while (j >= 0 && i < matrix.length) {
      if (matrix[i][j] < 0) {
        count += j + 1;
        i++;
      } else {
        j--;
      }
    }
    return count;
  }
}
//time: O(n + m), space: O(1)
