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
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int n = A.length;
        int[][] opt = new int[n * 2][n * 2];
        boolean[][] flag = new boolean[n * 2][n * 2];
        int[][] sum = new int[n * 2][n * 2];
        for (int i = 0; i < n * 2; ++i) {
            sum[i][i] = A[i % n];
            for (int j = i + 1; j < n * 2; ++j) {
                if (i != (j % n)) {
                    sum[i][j] = sum[i][j - 1] + A[j % n];
                }
            }
        }
        
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            result = Math.min(memorySearch(A, opt, flag, sum, i, i + n - 1), result);
        }
        return result;
    }
    
    private int memorySearch(int[] A, int[][] opt, boolean[][] flag, int[][] sum,
                                int i, int j) {
        if (flag[i][j]) {
            return opt[i][j];
        }
        flag[i][j] = true;
        
        int count = Integer.MAX_VALUE;
        if (i == j) {
            opt[i][j] = 0;
        } else {
            opt[i][j] = Integer.MAX_VALUE;
            for (int k = i; k < j; ++k) {
                int left = memorySearch(A, opt, flag, sum, i, k);
                int right = memorySearch(A, opt, flag, sum, k + 1, j);
                opt[i][j] = Math.min(opt[i][j], left + right + sum[i][j]);
            }
        }
        return opt[i][j];
    }
}
//time: O(n^n), space: O(n^2)