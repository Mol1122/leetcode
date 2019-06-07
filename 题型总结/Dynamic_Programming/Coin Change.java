/* You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin. */ 

public class Solution {
  public int coinChange(int[] coins, int amount) {
    if (coins == null || coins.length == 0) {
      return 0;
    }
    int[] f = new int[amount + 1];
    f[0] = 0;

    for (int i = 1; i <= amount; i++) {
      f[i] = Integer.MAX_VALUE;
      for (int j = 0; j < coins.length; j++) {
        if (coins[j] <= i && f[i - coins[j]] != Integer.MAX_VALUE) {
          f[i] = Math.min(f[i], f[i - coins[j]] + 1);
        }
      }
    }
    return f[amount] == Integer.MAX_VALUE ? -1 : f[amount];
  }
}
//f[i] = 要凑成amount i，最少需要多少枚硬币
//time: O(n^2), space: O(n)