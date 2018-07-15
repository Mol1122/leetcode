class Solution {
    public int maxVacationDays(int[][] flights, int[][] days) {
        if (flights == null || days == null) {
            return 0;
        }
        int N = flights.length;
        int K = days[0].length;
        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        
        for (int i = 0; i < K; i++) {
            int[] temp = new int[N]; //记录第j个城市在第i周的最大值
            Arrays.fill(temp, Integer.MIN_VALUE);
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (j == k || flights[k][j] == 1) { //易漏
                        temp[j] = Math.max(temp[j], dp[k] + days[j][i]);
                    }            
                }
            }
            dp = temp;
        }
        int max = 0;
        for (int v : dp) {
            max = Math.max(max, v);
        }
        return max;
    }
}

/* 算法：可以用dfs去遍历所有的城市在所有城市的情况，O(N^K)会超时. 动态规划。 
**      state: dp[i][j] = 第i周在第j个城市的最大值 
**      function: dp[i][j] = max(dp[i - 1][k], days[j][i]), 从上一周的k城市到这一周的i城市
**      因为当前周的情况只跟上一周有关系，所以可以compress成一维    
** 难点：dp和temp的相互交替去实现降维!!, 以及需要判断有没有航班，自己去自己的城市也算航班，但是值为0
** 时间复杂度：O(K*N*N)
** 空间复杂度：O(N)
*/