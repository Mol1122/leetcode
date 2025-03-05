/* Given an array of n * m matrix, and a moving matrix window (size k * k), move the window from top left to bottom right at each iteration, find the maximum sum inside the window at each moving.
Return 0 if the answer does not exist.


Example
Example 1:

Input：[[1,5,3],[3,2,1],[4,1,9]]，k=2
Output：13
Explanation：
At first the window is at the start of the matrix like this

    [
      [|1, 5|, 3],
      [|3, 2|, 1],
      [4, 1, 9],
    ]
,get the sum 11;
then the window move one step forward.

    [
      [1, |5, 3|],
      [3, |2, 1|],
      [4, 1, 9],
    ]
,get the sum 11;
then the window move one step forward again.

    [
      [1, 5, 3],
      [|3, 2|, 1],
      [|4, 1|, 9],
    ]
,get the sum 10;
then the window move one step forward again.

    [
      [1, 5, 3],
      [3, |2, 1|],
      [4, |1, 9|],
    ]
,get the sum 13;
SO finally, get the maximum from all the sum which is 13.
Example 2:

Input：[[10]，k=1
Output：10
Explanation：
sliding window size is 1*1，and return 10.
Challenge
O(n^2) time. */

public class Solution {
    /**
     * @param matrix: an integer array of n * m matrix
     * @param k: An integer
     * @return: the maximum number
     */
    public int maxSlidingMatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] sums = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sums[i][j] = sums[i - 1][j] + sums[i][j - 1] + matrix[i - 1][j - 1] - sums[i - 1][j - 1];
            }
        }
        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i >= k && j >= k) {
                    max = Math.max(max, sums[i][j] - sums[i - k][j] - sums[i][j - k] + sums[i - k][j - k]);
                }
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }
}
//time: O(n^2), space: O(n^2)