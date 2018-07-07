public class Solution {
    /**
     * @param n: Your money
     * @param prices: Cost of each university application
     * @param probability: Probability of getting the University's offer
     * @return: the  highest probability
     */
    public double backpackIX(int n, int[] prices, double[] probability) {
        if (prices == null || prices.length == 0) {
            return 0.0;
        }
        double[] dp = new double[n + 1];
        Arrays.fill(dp, 1);
        int m = prices.length;
        
        for (int i = 0; i < m; i++) {
            for (int j = n; j >= prices[i]; j--) {
                dp[j] = Math.min(dp[j], dp[j - prices[i]] * (1 - probability[i]));
            }
        }
        return 1 - dp[n];
    }
}

/* 算法：dp[j]表示当钱的数量为j时，被大学录取的可能性。因为题目是至少找到一份工作，所以要转化成都不可能找到工作的可能性。两个事件发生的概率等于两个分别的概率相乘
** 时间复杂度：O(nm)
** 空间复杂度：O(n) */