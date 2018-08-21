public class Solution {
    /**
     * @param n: An integer
     * @return: A boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        boolean[] f = new boolean[n + 1];
        if (n == 0) {
            return false;
        }
        if (n <= 2) {
            return true;
        }
        f[1] = true;
        f[2] = true;
        for (int i = 3; i <= n; i++) {
            f[i] = f[i - 1] == false || f[i - 2] == false;
        }
        return f[n];
    }
}

/* 算法：f[i] = f[i-1] == FALSE OR f[i-2] == FALSE 表示还剩i个石子时，当前先手时候会取胜
** 时间复杂度: O(n)
** 空间复杂度：O(n), 优化后可以到O(1) */