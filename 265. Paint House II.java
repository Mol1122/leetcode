class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        // min1 is the index of the 1st-smallest cost till previous house
        // min2 is the index of the 2nd-smallest cost till previous house
        int min1 = -1, min2 = -1;
        int n = costs.length, k = costs[0].length;
        
        for (int i = 0; i < n; i++) {
            int last1 = min1, last2 = min2;
            min1 = -1;
            min2 = -2;
            
            for (int j = 0; j < k; j++) {
                if (j != last1) {
                    costs[i][j] += last1 == -1 ? 0 : costs[i - 1][last1];
                } else {
                    costs[i][j] += last2 == -1 ? 0 : costs[i - 1][last2];
                }
                //update the 1st and 2nd smallest in the row i
                if (min1 < 0 || costs[i][j] < costs[i][min1]) {
                    min2 = min1;
                    min1 = j;
                } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }
        return costs[n - 1][min1];
    }
}

/* 算法：动态规划。跟paint house I的思路一样。cost[i][j] = 当前paint house i with color j的最小cost，要保证之前的color没有挨着的相同。
        min1, min2去记录，最小的和第二小的cost的color index*/ 