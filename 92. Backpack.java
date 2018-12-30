public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        //终极优化
        // if (A == null || A.length == 0) {
        //     return 0;
        // }
        // int n = A.length;
        // boolean[] f = new boolean[m + 1];
        
        // f[0] = true;
        // for (int i = 1; i <= m; i++) {
        //     f[i] = false;
        // }
        
        // for (int i = 1; i <= n; i++) {
        //     for (int j = m; j >= A[i - 1]; j--) {
        //         f[j] |= f[j - A[i - 1]];
        //     }
        // }
        // for (int i = m; i >= 0; i--) {
        //     if (f[i]) {
        //         return i;
        //     }
        // }
        // return 0;
        
      
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        for (int i = 1; i <= m; i++) {
            f[0][i] = false;
        }
        
        //遍历第i个物品
        for (int i = 1; i <= n; i++) {
            //遍历所有可能的重量0~m
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j]; //不放最后一个物品
                if (j >= A[i - 1]) { //放最后一个物品
                    f[i][j] |= f[i - 1][j - A[i - 1]];
                }
            }
        }
        //从大到小，找出可能的最大重量
        for (int i = m; i >= 0; i--) {
            if (f[n][i]) {
                return i;
            }
        }
        return 0;
        
        //动态规划班
        // if (A == null || A.length == 0) {
        //     return 0;
        // }
        // int n = A.length;
        // boolean[][] f = new boolean[n + 1][m + 1];
        
        // f[0][0] = true;
        // for (int i = 1; i <= m; i++) {
        //     f[0][i] = false;
        // }
        
        // for (int i = 1; i <= n; i++) {
        //     for (int j = 0; j <= m; j++) {
        //         f[i][j] = f[i - 1][j];
        //         if (j >= A[i - 1]) {
        //             f[i][j] |= f[i - 1][j - A[i - 1]];
        //         }
        //     }
        // }
        // for (int i = m; i >= 0; i--) {
        //     if (f[n][i]) {
        //         return i;
        //     }
        // }
        // return 0;
        
        
        
        // int n = A.length;
        // int[] f = new int[m + 1];
        
        // for (int i = 0; i < n; i++) {
        //     for (int j = m; j >= A[i]; j--) {
        //         f[j] = Math.max(f[j], f[j - A[i]] + A[i]);
        //     }
        // }
        // return f[m];
    }
}

/* 动态规划班思想：
** 时间复杂度：O(MN),
** 优化后空间复杂度：O(M), 难点：不可以用mod2去优化row

算法：f[i][w] = 能否用前i个物品拼出重量w
可以把每个物品的大小当做每个物品的价值，这样就可以用0-1背包的问题解决 */