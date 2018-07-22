public class Solution {
    /**
     * @param n: An integer
     * @return: An Integer
     */
    public int climbStairs2(int n) {
        if (n < 0) {
            return 0;
        }
        //dp[i] = 爬到i个楼梯时候的方案数
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        for (int i = 0; i < n + 1; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i >= j) {
                    dp[i] += dp[i - j];
                }
            }
        }
        return dp[n];
        
    }
}

/* 算法：动态规划。跟Climbing Stairs I一个思路
** 时间复杂度：O(n) */