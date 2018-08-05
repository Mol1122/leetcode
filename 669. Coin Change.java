public class Solution {
    /**
     * @param coins: a list of integer
     * @param amount: a total amount of money amount
     * @return: the fewest number of coins that you need to make up
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int[] f = new int[amount + 1];
        f[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                //last coin A[0], A[1],...,A[j]
                if (coins[j] <= i && f[i - coins[j]] != Integer.MAX_VALUE) {
                    f[i] = Math.min(f[i], f[i - coins[j]] + 1);
                }
            }
        }
        if (f[amount] == Integer.MAX_VALUE) {
            f[amount] = -1;
        }
        return f[amount];
    }
}

/* 状态：f[i] = 要凑成amount i, 最少需要多少枚硬币 */