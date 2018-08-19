public class Solution {
    /**
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }
        int n = A.length;
        int m = A[0].length;
        int[][] f = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] == 1) {
                    f[i][j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    f[i][j] = 1;
                    continue;
                }
                if (i > 0) {
                    f[i][j] += f[i - 1][j];
                }
                if (j > 0) {
                    f[i][j] += f[i][j - 1];
                }
            }
        }
        return f[n - 1][m - 1];
    }
}

/* 算法：坐标型DP. 
** 技巧：可以在loop里面进行初始化 
** 时间复杂度：O(nm) */