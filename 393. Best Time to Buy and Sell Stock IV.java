public class Solution {
    /**
     * @param K: An integer
     * @param prices: An integer array
     * @return: Maximum profit
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        if (k >= n/ 2) {
            return helper(prices);
        }
        // local[i][j] 表示前i天，至多进行j次交易，第i天必须sell的最大获益
//         int[][] local = new int[n][k + 1];
        // global[i][j] 表示前i天，至多进行j次交易，第i天可以不sell的最大获益
//         int[][] global = new int[n][k +1];
        
//         for (int i = 1; i < n; i++) {
//             int diff = prices[i] - prices[i - 1];
//             for (int j = 1; j <= k; j++) {
//                 local[i][j] = Math.max(global[i - 1][j - 1] + diff, local[i - 1][j] + diff);
//                 global[i][j] = Math.max(global[i - 1][j], local[i][j]);
//             }
//         }
//         return global[n - 1][k];
      
        int[] local = new int[k + 1];
        int[] global = new int[k + 1];
        
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int j = k; j > 0; j--) { //如果需要降维，那么需要倒序来写，并且j的dimension完全保留
                local[j] = Math.max(global[j - 1], local[j] + diff);
                global[j] = Math.max(global[j], local[j]);
            }
        }
        return global[k];
    }
    
    private int helper(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit  += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}

/* 算法：动态规划。可以对比背包问题
** 时间复杂度：O(nk) 
** 难点：1. for loop里一定要先计算local再计算global
***     2. (local[i - 1][j] + diff) 在第i-1天时，恰好已经交易了j次（local[i-1][j]），那么如果i-1天到i天再交易一次：即在第i-1天买入，第i天卖出（diff），则这不并不会增加交易次数！ */

// https://aaronice.gitbooks.io/lintcode/content/high_frequency/best_time_to_buy_and_sell_stock_iv.html