public class Solution {
    /**
     * @param costs: n x 3 cost matrix
     * @return: An integer, the minimum cost to paint all houses
     */
    public int minCost(int[][] costs) {
        //坐标型动态规划做法
        // if (costs == null || costs.length == 0) {
        //     return 0;
        // }
        // int n = costs.length;
        // for (int i = 1; i < n; i++) {
        //     costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
        //     costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
        //     costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        // }
        // return Math.min(Math.min(costs[n - 1][0], costs[n - 1][1]), costs[n - 1][2]);
        
        //序列型dp, 时间复杂度：0(n), 空间复杂度：O(n)
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length;
        int[][] f = new int[n + 1][3];
        
        f[0][0] = f[0][1] = f[0][2] = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                f[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < 3; k++) {
                    if (j != k) {
                        f[i][j] = Math.min(f[i][j], f[i - 1][k] + costs[i - 1][j]);
                    }
                }
            }
        }
        return Math.min(f[n][0], Math.min(f[n][1], f[n][2]));
    }
}
