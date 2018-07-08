public class Solution {
    /*
     * @param A: An integer array
     * @param target: An integer
     * @return: An integer
     */
    public int MinAdjustmentCost(List<Integer> A, int target) {
        if (A == null || A.size() == 0) {
            return 0;
        }
        int n = A.size();
        //state: dp[i][j] = 第i个数，调整后为j的额值，调整前的值为k，的最小代价
        int[][] dp = new int[n][101]; //假设调整后的数值可能为[0, 100]
        for (int i = 0; i <= 100; i++) {
            dp[0][i] = Math.abs(i - A.get(0));
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 100; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                int diff = Math.abs(j - A.get(i));
                int upper = Math.min(100, j + target);
                int lower = Math.max(0, j - target);
                
                for (int k = lower; k <= upper; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + diff);
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= 100; i++) {
            result = Math.min(result, dp[n - 1][i]);
        }
        return result;
    }
}