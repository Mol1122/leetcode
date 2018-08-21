public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        //终极优化版
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
            for (int j = m; j >= A[i - 1]; j--) {
                if (f[j - A[i - 1]] != -1) {
                    f[j] = Math.max(f[j], f[j - A[i - 1]] + V[i - 1]);
                }
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
        //         if (j >= A[i - 1] && f[i - 1][j - A[i - 1]] != -1) {
        //             f[i][j] = Math.max(f[i][j], f[i - 1][j - A[i - 1]] + V[i - 1]);
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

/* 算法：f[i][w] = 用前i个物品拼出重量w时最大总价值(-1表示不能拼出w) */