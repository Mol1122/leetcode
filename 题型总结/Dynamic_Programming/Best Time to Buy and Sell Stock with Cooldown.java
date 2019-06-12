/* Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day) */

public class Solution {
  public int maxProfit(int[] prices) {
    if (prices == null || prices.length <= 1) {
        return 0;
    }
    int n = prices.length;
    int[] hold = new int[3];
    int[] unhold = new int[3];
    hold[0] = -prices[0];

    for (int i = 1; i < prices.length; i++) {
        if (i == 1) {
            hold[i%3] = Math.max(hold[(i - 1)%3], -prices[1]);
        } else {
            hold[i%3] = Math.max(hold[(i - 1)%3], unhold[(i - 2)%3] - prices[i]);
        }
        unhold[i%3] = Math.max(unhold[(i - 1)%3], hold[(i - 1)%3] + prices[i]);
    }
    return unhold[(n - 1)%3];
    
/*    if (prices == null || prices.length <= 1) {
        return 0;
    }
    int n = prices.length;
    int[] hold = new int[n];
    int[] unhold = new int[n];
    hold[0] = -prices[0];

    for (int i = 1; i < prices.length; i++) {
        if (i == 1) {
            hold[i] = Math.max(hold[i - 1], -prices[1]);
        } else {
            hold[i] = Math.max(hold[i - 1], unhold[i - 2] - prices[i]);
        }
        unhold[i] = Math.max(unhold[i - 1], hold[i - 1] + prices[i]);
    }
    return unhold[n - 1];  */
  }
}
//time: O(n), space: O(n) -> O(1)