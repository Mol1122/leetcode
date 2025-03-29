/* Description
Given a knight in a chessboard n * m (a binary matrix with 0 as empty and 1 as barrier). the knight initialze position is (0, 0) and he wants to reach position (n - 1, m - 1), Knight can only be from left to right. Find the shortest path to the destination position, return the length of the route. Return -1 if knight can not reached.

LintCode - Online Judge Solution

Candidate Written Test Screening, Team Competency Assessment, Programming Teaching Exercises, Online Exam Grading

WeChat for information（ID chenleo0002）


If the knight is at (x, y), he can get to the following positions in one step:

(x + 1, y + 2)
(x - 1, y + 2)
(x + 2, y + 1)
(x - 2, y + 1)
Example
Example 1:

Input:
[[0,0,0,0],[0,0,0,0],[0,0,0,0]]
Output:
3
Explanation:
[0,0]->[2,1]->[0,2]->[2,3]
Example 2:

Input:
[[0,1,0],[0,0,1],[0,0,0]]
Output:
-1
 */

public class Solution {
    /**
     * @param grid: a chessboard included 0 and 1
     * @return: the shortest path
     */
    public int shortestPath2(boolean[][] grid) {
        //BFS，时间复杂度:O(nm)
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        int steps = 0;
        
        int[] dx = {1, -1, 2, -2};
        int[] dy = {2, 2, 1, 1};
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] c = queue.poll();
                if (c[0] == n - 1 && c[1] == m - 1) {
                    return steps;
                }
                for (int i = 0; i < 4; i++) {
                    int[] np = new int[2];
                    np[0] = c[0] + dx[i];
                    np[1] = c[1] + dy[i];
                    if (np[0] >= 0 && np[0] < n && np[1] >= 0 && np[1] < m && !grid[np[0]][np[1]]) {
                        queue.offer(np);
                        grid[np[0]][np[1]] = true;
                    }
                }
            }
            steps++;
        }
        return -1;
        // //dp, dp[i][j] = 从[0][0]要走多少步到达当前点[i][j]
        // if (grid == null || grid.length == 0 || grid[0].length == 0) {
        //     return -1;
        // }
        // int n = grid.length;
        // int m = grid[0].length;
        // int[][] dp = new int[n][m];
        
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         dp[i][j] = Integer.MAX_VALUE;
        //     }
        // }
        // dp[0][0] = 0;
        // for (int j = 1; j < m; j++) { //难点
        //     for (int i = 0; i < n; i++) {
        //         if (!grid[i][j]) {
        //             //可以从上一个点走到
        //             if (i >= 1 && j >= 2 && dp[i - 1][j - 2] != Integer.MAX_VALUE) {
        //                 dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 2] + 1);
        //             }
        //             if (i + 1 < n && j >= 2 && dp[i + 1][j - 2] != Integer.MAX_VALUE) {
        //                 dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 2] + 1);
        //             }
        //             if (i >= 2 && j >= 1 && dp[i - 2][j - 1] != Integer.MAX_VALUE) {
        //                 dp[i][j] = Math.min(dp[i][j], dp[i - 2][j - 1] + 1);
        //             }
        //             if (i + 2 < n && j >= 1 && dp[i + 2][j - 1] != Integer.MAX_VALUE) {
        //                 dp[i][j] = Math.min(dp[i][j], dp[i + 2][j - 1] + 1);
        //             }
        //         }
        //     }
        // }
        // if (dp[n - 1][m - 1] == Integer.MAX_VALUE) {
        //     return -1;
        // }
        // return dp[n - 1][m - 1];
    }
}

/* 算法：普通dp
** 时间复杂度：O(nm)
** 难点：因为有从左往右的限制，所以最外层循环要是j,否则从上往下就不满足限制了 */