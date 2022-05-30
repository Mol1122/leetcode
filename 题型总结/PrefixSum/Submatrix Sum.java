/* Given an integer matrix, find a submatrix where the sum of numbers is zero. Your code should return the coordinate of the left-up and right-down number.

If there are multiple answers, you can return any of them.

Example
Example 1:

Input:
[
  [1, 5, 7],
  [3, 7, -8],
  [4, -8 ,9]
]
Output: [[1, 1], [2, 2]]
Example 2:

Input:
[
  [0, 1],
  [1, 0]
]
Output: [[0, 0], [0, 0]]
Challenge
O(n3) time. */

public class Solution {
    /**
     * @param matrix: an integer matrix
     * @return: the coordinate of the left-up and right-down number
     */
    public int[][] submatrixSum(int[][] matrix) {
        int[][] result = new int[2][2];
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] sums = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sums[i][j] = sums[i - 1][j] + sums[i][j - 1] + matrix[i - 1][j - 1] - sums[i - 1][j - 1];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                Map<Integer, Integer> sum2index = new HashMap<>();
                for (int k = 0; k <= m; k++) {
                    int sum = sums[i][k] - sums[j][k];
                    if (sum2index.containsKey(sum)) {
                        int col = sum2index.get(sum);
                        result[0][0] = i;
                        result[0][1] = col;
                        result[1][0] = j - 1;
                        result[1][1] = k - 1;
                        return result;
                    } else {
                        sum2index.put(sum, k);
                    }
                }
            }
        }
        return result;
    }
}