public class Solution {
    /**
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */
    public int uniquePaths(int m, int n) {
        int[][] f = new int[2][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    f[i%2][j] = 1;
                }
                else {
                    f[i%2][j] = f[(i-1)%2][j] + f[i%2][j-1];
                }
            }
        }
        
        return f[(m-1)%2][n-1];
    }
}

/* 算法：dynamic programming. 状态，方程，赋值，答案
** 空间优化：只需要保存两行就能求出新值，所以mod 2*/