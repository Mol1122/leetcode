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
        int[][] memo = new int[2 * n][2 * n];
        boolean[][] visited = new boolean[2 * n][2 * n];
        int[][] sums = new int[2 * n][2 * n];
        
        for (int i = 0; i < 2 * n; i++) {
            sums[i][i] = A[i % n];
            for (int j = i + 1; j < 2 * n; j++) {
                if (i != (j % n)) {
                    sums[i][j] = sums[i][j - 1] + A[j % n];
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, memorySearch(A, memo, visited, sums, i, i + n - 1));
        }
        return min;
    }
    
    private int memorySearch(int[] A, int[][] memo, boolean[][] visited, int[][] sums, int start, int end) {
        if (visited[start][end]) {
            return memo[start][end];
        }
        visited[start][end] = true;
        if (start == end) {
            memo[start][end] = 0;
        } else {
            memo[start][end] = Integer.MAX_VALUE;
            for (int k = start; k < end; k++) {
                int left = memorySearch(A, memo, visited, sums, start, k);
                int right = memorySearch(A, memo, visited, sums, k + 1, end);
                memo[start][end] = Math.min(memo[start][end], left + right + sums[start][end]);
            }
        }
        return memo[start][end];
    }
}
//time: O(n^3), space: O(n^2)