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
        int[][] f = new int[2][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                f[i%2][j] = Integer.MAX_VALUE;
                if (i == 0 && j == 0) {
                    f[i%2][j] = grid[0][0];
                    continue;
                }
                if (i > 0) {
                    f[i%2][j] = Math.min(f[i%2][j], f[(i - 1)%2][j] + grid[i][j]);
                }
                if (j > 0) {
                    f[i%2][j] = Math.min(f[i%2][j], f[i%2][j - 1] + grid[i][j]);
                }
            }
        }
        return f[(n - 1)%2][m - 1];
    }
}