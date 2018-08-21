public class Solution {
    /**
     * @param A: an integer array
     * @param V: an integer array
     * @param m: An integer
     * @return: an array
     */
    public int backPackIII(int[] A, int[] V, int m) {
        //终极优化，计算顺序是从左往右
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[] f = new int[m + 1];
        f[0] = 0;
        for (int i = 1; i <= m; i++) {
            f[i] = -1; //impossible
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int now = f[j];
                if (j >= A[i - 1] && f[j - A[i - 1]] != -1) {
                    now = Math.max(now, f[j - A[i - 1]] + V[i - 1]);
                }
                f[j] = now;
            }
        }
        int res = 0;
        for (int i = m; i >= 0; i--) {
            if (f[i] != -1) {
                res = Math.max(res, f[i]);
            }
        }
        return res;
        
        // if (A == null || A.length == 0) {
        //     return 0;
        // }
        // int n = A.length;
        // int[][] f = new int[n + 1][m + 1];
        // f[0][0] = 0;
        // for (int i = 1; i <= m; i++) {
        //     f[0][i] = -1; //impossible
        // }
        
        // for (int i = 1; i <= n; i++) {
        //     for (int j = 0; j <= m; j++) {
        //         f[i][j] = f[i - 1][j];
        //         if (j >= A[i - 1] && f[i][j - A[i - 1]] != -1) {
        //             f[i][j] = Math.max(f[i][j], f[i][j - A[i - 1]] + V[i - 1]);
        //         }
        //     }
        // }
        // int res = 0;
        // for (int i = m; i >= 0; i--) {
        //     if (f[n][i] != -1) {
        //         res = Math.max(res, f[n][i]);
        //     }
        // }
        // return res;
    }
}

/* 算法：f[i][w] = 前i种物品拼出重量w时最大总价值(-1表示不能拼出w) 
** 难点: 与backupii的唯一区别就是line 23, 这个是根据画图得到的，而不是凭空想 */