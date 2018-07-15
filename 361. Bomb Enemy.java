class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int row = 0; //number of E in a row
        int[] col = new int[m]; //number of E in a col
        int result = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0 || grid[i][j - 1] == 'W') {
                    row = 0;
                    for (int k = j; k < m && grid[i][k] != 'W'; k++) {
                        if (grid[i][k] == 'E') {
                            row++;
                        }
                    }
                }
                if (i == 0 || grid[i - 1][j] == 'W') {
                    //System.out.println("j = " + j);
                    col[j] = 0;
                    for (int k = i; k < n && grid[k][j] != 'W'; k++) {
                        if (grid[k][j] == 'E') {
                            col[j]++;
                        }
                    }
                }
                if (grid[i][j] == '0' && row + col[j] > result) {
                    result = row + col[j];
                }
            }
        }
        return result;
    }
}

/* 算法：动态规划。棋盘类型
** 时间复杂度：O(nm(n + m)) in the worst case 
** 空间复杂度：O(m) */

