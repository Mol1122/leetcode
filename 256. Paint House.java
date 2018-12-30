class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        //定义dp数组
        int[][] f = new int[n + 1][3];
        //初始值
        f[0][0] = f[0][1] = f[0][2] = 0;
        
        //计算顺序：从第一个到第n个的遍历
        for (int i = 1; i <= n; i++) {
            //遍历第i个位置(A[i - 1])的可能值
            for (int j = 0; j < 3; j++) {
                //赋初始值
                f[i][j] = Integer.MAX_VALUE;
                
                //遍历第i-1个位置的(A[i - 2])的可能值
                for (int k = 0; k < 3; k++) {
                    if (j != k) { //满足条件才能更新
                        f[i][j] = Math.min(f[i][j], f[i - 1][k] + costs[i - 1][j]);
                    }
                }
            }
        }
        //在最后一个位置上的所有状态中找最小的一个
        return Math.min(f[n][0], Math.min(f[n][1], f[n][2]));
    }
}

/* f[i][0], f[i][1], f[i][2] = 油漆前i栋房子并且房子i - 1是红色，蓝色，绿色的最小花费
** 转移方程：f[i][0] = min{f[i-1][1] + cost[i-1][0], f[i-1][2] + cost[i-1][0]} 
** */