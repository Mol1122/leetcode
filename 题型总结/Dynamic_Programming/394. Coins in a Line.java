/* There are n coins in a line. Two players take turns to take one or two coins from right side until 
there are no more coins left. The player who take the last coin wins.

Could you please decide the first player will win or lose?

If the first player wins, return true, otherwise return false.

Example
Example 1:

Input: 1
Output: true
Example 2:

Input: 4
Output: true
Explanation:
The first player takes 1 coin at first. Then there are 3 coins left.
Whether the second player takes 1 coin or two, then the first player can take all coin(s) left.
Challenge
O(n) time and O(1) memory */

public class Solution {
    /**
     * @param n: An integer
     * @return: A boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        boolean[] f = new boolean[3];
        if (n == 0) {
            return false;
        }
        if (n <= 2) {
            return true;
        }
        f[0] = false;
        f[1] = true;
        f[2] = true;
        
        for (int i = 3; i <= n; i++) {
            f[i%3] = f[(i - 1)%3] == false || f[(i - 2)%3] == false;
        }
        return f[n%3];
        
        
        
        
        // boolean[] f = new boolean[n + 1]; //面对i个石子，是否先手必胜
        // if (n == 0) {
        //     return false;
        // }
        // if (n <= 2) {
        //     return true;
        // }
        // f[1] = true;
        // f[2] = true;
        // for (int i = 3; i <= n; i++) {
        //     f[i] = f[i - 1] == false || f[i - 2] == false;
        // }
        // return f[n];
    }
}

/* 算法：f[i] = f[i-1] == FALSE OR f[i-2] == FALSE 表示还剩i个石子时，当前先手时候会取胜
** 时间复杂度: O(n)
** 空间复杂度：O(n), 优化后可以到O(1) */