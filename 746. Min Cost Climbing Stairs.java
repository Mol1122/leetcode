class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1]; //防止溢出
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length];
    }
}

/* 算法：dynamic programming
** 难点：dp的长度是cost.length + 1, 因为假设有三个楼梯，一定要走完最后一层楼梯(走到n+1层)才能得出结论。并且楼梯是从0或1开始，那么
**      要开的长度就是cost.length + 1*/