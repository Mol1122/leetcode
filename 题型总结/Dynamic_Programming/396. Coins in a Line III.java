/* There are n coins in a line, and value of i-th coin is values[i].

Two players take turns to take a coin from one of the ends of the line until there are no more 
coins left. The player with the larger amount of money wins.

Could you please decide the first player will win or lose?

Example
Example 1:

Input: [3, 2, 2]
Output: true
Explanation: The first player takes 3 at first. Then they both take 2.
Example 2:

Input: [1, 20, 4]
Output: false
Explanation: The second player will take 20 whether the first player take 1 or 4.
Challenge
O(1) memory and O(n) time when n is even. */

public class Solution {
    /**
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }
        int n = A.length;
        int[][] f = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            f[i][i] = A[i]; //当前先手只剩一个数可以取，后手没有数字可以取，差值就是A[i]
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = Math.max(-f[i + 1][j] + A[i], -f[i][j - 1] + A[j]);
            }
        }
        return f[0][n - 1] >= 0;
    }
}

/* 算法：博弈类dp + 区间型dp, 设f[i][j]为一方先手在面对a[i..j]这些数字时，能得到的最大的与对
                              手的数字差
** 难点：设己方数字和是A，对手数字和是B，即目标是A>=B，等价于A-B>=0 
**       当一方X面对剩下的数字，可以认为X就是当前的先手，他的目标就是最
            大化SX=X-Y   
         对于X来说，Sx = -Sy + m */