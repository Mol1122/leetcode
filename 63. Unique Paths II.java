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
    }
}