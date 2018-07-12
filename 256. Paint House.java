class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        for (int i = 1; i < n; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        System.out.println(costs[n - 1][0]);
        System.out.println(costs[n - 1][1]);
        System.out.println(costs[n - 1][2]);
        return Math.min(Math.min(costs[n - 1][0], costs[n - 1][1]), costs[n - 1][2]);
    }
}

/* 算法：普通的动态规划
**      dp[i][j] = 第i个房子paint涂上颜色j加上i之前paint的cost 
** 时间复杂度：O(n) */                          