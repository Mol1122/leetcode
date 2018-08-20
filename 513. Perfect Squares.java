public class Solution {
    /**
     * @param n: a positive integer
     * @return: An integer
     */
    public int numSquares(int n) {
        int[] f = new int[n + 1];
        f[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            //枚举最后一个数j
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                f[i] = Math.min(f[i], f[i - j * j] + 1);
            }
        }
        return f[n];
    }
}

/* 算法：划分型动态规划。f[i]表示i划分成完全平方数的最少个数
** 时间复杂度：O(nsqrt(n)) 
** 空间复杂度：O(n) */