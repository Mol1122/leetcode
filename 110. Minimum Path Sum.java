class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int[][] f = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                f[i][j] = Integer.MAX_VALUE;
                if (i == 0 && j == 0) {
                    f[i][j] = grid[0][0];
                    continue;
                }
                if (i > 0) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j] + grid[i][j]);
                }
                if (j > 0) {
                    f[i][j] = Math.min(f[i][j], f[i][j - 1] + grid[i][j]);
                }
            }
        }
        return f[n - 1][m - 1];
    }
}

/* 算法：坐标型dp 
** 时间复杂度：O(nm)
** 空间复杂度：O(nm) */