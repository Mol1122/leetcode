class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
//         int n = prices.length;
//         int[] hold = new int[n];
//         int[] sold = new int[n];
//         hold[0] = -prices[0]; //难点!
        
//         for (int i = 1; i < n; i++) {
//             sold[i] = Math.max(sold[i - 1], hold[i - 1] + prices[i] - fee);
//             hold[i] = Math.max(hold[i - 1], sold[i - 1] - prices[i]);
//         }
//         return sold[n - 1];
        
        //空间优化：O(1)
        int hold = -prices[0], sold = 0;
        for (int i = 1; i < prices.length; i++) {
            int t = sold;
            sold = Math.max(sold, hold + prices[i] - fee);
            hold = Math.max(hold, t - prices[i]);
        }
        return sold;
    }
}

// http://www.cnblogs.com/grandyang/p/7776979.html