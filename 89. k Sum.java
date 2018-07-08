public class Solution {
    /**
     * @param A: An integer array
     * @param k: A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
    public int kSum(int[] A, int k, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[][][] dp = new int[n + 1][k + 1][target + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                for (int t = 1; t <= target; t++) {
                    dp[i][j][t] = dp[i - 1][j][t]; //我们可以不选择A[i - 1]这个值，
                                                //这种情况就是dp[i-1][j][t]，
                                                //也就是说直接在前-1个值里选择一些值加到target
                    if (t - A[i - 1] >= 0) {
                        dp[i][j][t] += dp[i - 1][j - 1][t - A[i - 1]]; //我们可以把当前
                                                                    //A[i-1]这个值包括进来，
                                                                    //所以需要加上dp[i-1][j-1][t-A[i-1]]
                                                                    //（前提是t-A[i-1]要大于0）
                    }
                }
            }
        }
        return dp[n][k][target];
    }
}

/* 算法：背包类dp
         dp[i][j][t] = 前i个元素中选j个,组合成为t的个数
         function：dp[i][j][t] = dp[i - 1][j - 1][t - A[i - 1]] + dp[i - 1][j][t]  ->能放进去
                               = dp[i - 1][i][t] ->不能放进去
         
         */