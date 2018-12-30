public class Solution {
    /**
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */
    public int uniquePaths(int m, int n) {
        //定义dp数组
        int[][] f = new int[m][n];
        
        //计算顺序：从小往大
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //初始化
                if (i == 0 || j == 0) {
                    f[i][j] = 1;
                    continue;
                }
                //后一步和前一步的关系，由转移方程得到
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }
}

/* 算法：dynamic programming */