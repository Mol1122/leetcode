/* A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the 
bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?



An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note: m and n will be at most 100.

Example 1:

Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right */

class Solution {
    public int uniquePathsWithObstacles(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int[][] f = new int[n][m];
        
        //initialization
        for (int i = 0; i < n; i++) {
            if (grid[i][0] != 1) {
                f[i][0] = 1;
            } else {
                break;
            }
        }
        for (int j = 0; j < m; j++) {
            if (grid[0][j] != 1) {
                f[0][j] = 1;
            } else {
                break;
            }
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (grid[i][j] != 1) {
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
                } else {
                    f[i][j] = 0;
                }
            }
        }
        return f[n - 1][m - 1];
        
        
        //另一种更加简洁的写法
//         if (A == null || A.length == 0 || A[0].length == 0) {
//             return 0;
//         }
//         int n = A.length;
//         int m = A[0].length;
//         int[][] f = new int[n][m];
        
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
//                 if (A[i][j] == 1) {
//                     f[i][j] = 0;
//                     continue;
//                 }
//                 if (i == 0 && j == 0) {
//                     f[i][j] = 1;
//                     continue;
//                 }
//                 if (i > 0) {
//                     f[i][j] += f[i - 1][j];
//                 }
//                 if (j > 0) {
//                     f[i][j] += f[i][j - 1];
//                 }
//             }
//         }
//         return f[n - 1][m - 1];
    }
}