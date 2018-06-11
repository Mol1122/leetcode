public class Solution {
    /**
     * @param matrix: a matrix of 0 an 1
     * @return: an integer
     */
    public int maxSquare2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] f = new int[n][m];
        int[][] u = new int[n][m];
        int[][] l = new int[n][m];
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    f[i][j] = 0;
                    u[i][j] = l[i][j] = 1;
                    if (i > 0) {
                        u[i][j] = u[i - 1][j] + 1;
                    }
                    if (j > 0) {
                        l[i][j] = l[i][j - 1] + 1;
                    }
                } else {
                    f[i][j] = 1;
                    u[i][j] = l[i][j] = 0;
                    if (i >0 && j > 0) {
                        f[i][j] = Math.min(f[i - 1][j - 1], Math.min(u[i - 1][j], l[i][j - 1])) + 1;
                    }
                }
                ans = Math.max(ans, f[i][j]);
            }
        }
        return ans * ans;
    }
}

/* 算法跟之前的原题一样 */