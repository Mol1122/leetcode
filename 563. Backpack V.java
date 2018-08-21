public class Solution {
    /**
     * @param nums: an integer array and all positive numbers
     * @param target: An integer
     * @return: An integer
     */
    public int backPackV(int[] A, int m) {
        //终极优化
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[] f = new int[m + 1];
        f[0] = 1;
        for (int i = 1; i <= m; i++) {
            f[i] = 0;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= A[i - 1]; j--) {
                f[j] += f[j - A[i - 1]];
                // int newVal = f[j] + f[j - A[i - 1]]; //正上方的老值 + 左上方的老值 覆盖掉当前的老值
                // f[j] = newVal;
            }
        }
        return f[m];
        
        //动态规划班
        // if (A == null || A.length == 0) {
        //     return 0;
        // }
        // int n = A.length;
        // int[][] f = new int[n + 1][m + 1];
        
        // f[0][0] = 1;
        // for (int i = 1; i <= m; i++) {
        //     f[0][1] = 0;
        // }
        
        // for (int i = 1; i <= n; i++) {
        //     for (int j = 0; j <= m; j++) {
        //         f[i][j] = f[i - 1][j];
        //         if (j >= A[i - 1]) {
        //             f[i][j] += f[i - 1][j - A[i - 1]];
        //         }
        //     }
        // }
        // return f[n][m];
        
        // int n = nums.length;
        // int[] f = new int[target + 1];
        // f[0] = 1;
        
        // for (int i = 0; i < nums.length; i++) {
        //     for (int j = target; j >= nums[i]; j--) {
        //         f[j] += f[j - nums[i]];
        //     }
        // }
        // return f[target];
    }
}

/* 算法：f[i][w] = 用前i个物品有多少种方式拼出重量w */