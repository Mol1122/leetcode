/* There is a stone game.At the beginning of the game the player picks n piles of stones in a line.

The goal is to merge the stones in one pile observing the following rules:

At each step of the game,the player can merge two adjacent piles to a new pile.
The score is the number of stones in the new pile.
You are to determine the minimum of the total score.

Example
Example 1:

Input: [3, 4, 3]
Output: 17
Example 2:

Input: [4, 1, 1, 4]
Output: 18
Explanation:
  1. Merge second and third piles => [4, 2, 4], score = 2
  2. Merge the first two piles => [6, 4]，score = 8
  3. Merge the last two piles => [10], score = 18 */

public class Solution {
    public int stoneGame(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[] sums = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + A[i - 1];
        }
        
        int[][] f = new int[n][n];
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j] + sums[j + 1] - sums[i]);
                }
            }
        }
        return f[0][n - 1];
    }
}
//time: O(n^3), space: O(n^2)