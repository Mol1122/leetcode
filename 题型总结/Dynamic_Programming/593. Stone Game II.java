/* There is a stone game.At the beginning of the game the player picks n piles of stones in a circle.

The goal is to merge the stones in one pile observing the following rules:

At each step of the game,the player can merge two adjacent piles to a new pile.
The score is the number of stones in the new pile.
You are to determine the minimum of the total score.

Example
Example 1:

Input:
[1,1,4,4]
Output:18
Explanation:
1. Merge second and third piles => [2, 4, 4], score +2
2. Merge the first two piles => [6, 4]，score +6
3. Merge the last two piles => [10], score +10
Example 2:

Input:
[1, 1, 1, 1]
Output:8
Explanation:
1. Merge first and second piles => [2, 1, 1], score +2
2. Merge the last two piles => [2, 2]，score +2
3. Merge the last two piles => [4], score +4 */

public class Solution {
    public int stoneGame2(int[] A) {
        if (A == null || A.length <= 1) {
            return 0;
        }
        int n = A.length;
        int[][] f = new int[2 * n][2 * n];
        int[] sums = new int[2 * n + 1];
        
        for (int i = 1; i <= 2 * n; ++i) {
            sums[i] = sums[i - 1] + A[(i - 1) % n];
        }
        for (int i = 0; i < 2 * n; i++) {
            f[i][i] = 0;
        }
        for (int i = 2 * n - 1; i >= 0; i--) {
            for (int j = i + 1; j < 2 * n; j++) {
                f[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j] + sums[j + 1] - sums[i]);   
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            result = Math.min(result, f[i][i + n - 1]);
        }
        return result;
    }
}
//time: O(n^3), space: O(n^2)