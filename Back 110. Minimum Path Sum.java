public class Solution {
    /**
     * @param grid: a list of lists of integers
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int[][] f = new int[n][m];
        f[0][0] = grid[0][0];
        
        for (int i = 1 ; i < n; i++) {
            f[i][0] = f[i-1][0] + grid[i][0];
        }
        for (int j = 1; j < m; j++) {
            f[0][j] = f[0][j-1] + grid[0][j];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                f[i][j] = Math.min(f[i-1][j], f[i][j-1]) + grid[i][j];
            }
        }
        return f[n-1][m-1];
    }
}

/* 算法：dynamic programming */