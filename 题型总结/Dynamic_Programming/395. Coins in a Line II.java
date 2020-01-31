/* There are n coins with different value in a line. Two players take turns to take one 
or two coins from left side until there are no more coins left. The player who take the 
coins with the most value wins.

Could you please decide the first player will win or lose?

If the first player wins, return true, otherwise return false.

Example
Example 1:

Input: [1, 2, 2]
Output: true
Explanation: The first player takes 2 coins.
Example 2:

Input: [1, 2, 4]
Output: false
Explanation: Whether the first player takes 1 coin or 2, the second player will gain more value. */

public class Solution {
    /**
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        int n = values.length;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + values[n - i];
        }
        int[] f = new int[n + 1];
        f[1] = values[n - 1];
        for (int i = 2; i <= n; i++) {
            f[i] = Math.max(sums[i] - f[i - 1], sums[i] - f[i - 2]);
        }
        return f[n] > sums[n] / 2;
    }
}

/* 算法：博弈类dp
** f[i] = the most values player can get if it's his turn when there are i coins left
** sum[i] = 后i个硬币的总价
** f[i] = max(sum[i] - f[i - 1], sum[i] - f[i - 2]) 先手取一个硬币或者先手取两个硬币 */