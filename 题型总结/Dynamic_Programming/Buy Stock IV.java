public class Solution {
  public int maxProfit(int[] prices, int k) {
    if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        if (k > n / 2) {
            return helper(prices);
        }
        int[][] f = new int[n + 1][2 * k + 1 + 1];
        for (int i = 1; i <= k; i++) {
            f[0][i] = Integer.MIN_VALUE;
        }
        f[0][1] = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 2 * k + 1; j += 2) {
                f[i][j] = f[i - 1][j];
                if (i >= 2 && j > 1 && f[i - 1][j - 1] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }
            for (int j = 2; j <= 2 * k + 1; j += 2) {
                f[i][j] = f[i - 1][j - 1];
                if (i >= 2 && f[i - 1][j] != Integer.MIN_VALUE) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j] + prices[i - 1] - prices[i - 2]);
                }   
            }
        }
        
        int result = 0;
        for (int i = 1; i <= 2 * k + 1; i++) {
            result = Math.max(result, f[n][i]);
        }
        return result;
  }
  private int helper(int[] prices) {
        int max = 0;
        
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            if (diff > 0) {
                max += diff;
            }
        }
        return max;
    }
}
//time: O(n^2), space: O(n * k)
