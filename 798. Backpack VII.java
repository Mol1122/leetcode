public class Solution {
    /**
     * @param n: the money of you
     * @param prices: the price of rice[i]
     * @param weight: the weight of rice[i]
     * @param amounts: the amount of rice[i]
     * @return: the maximum weight
     */
    public int backPackVII(int n, int[] prices, int[] weight, int[] amounts) {
        //price -> 体积
        //weight -> value
        
        //0-1背包的扩展
      /*  int m = prices.length;
        int[] f = new int[n + 1];
        
        for (int i = 0; i < m; i++) {
            for (int k = 1; k <= amounts[i]; k++) {
                for (int j = n; j >= prices[i]; j--) {
                    f[j] = Math.max(f[j], f[j - prices[i]] + weight[i]);
                }
            }
        }
        return f[n];  */
        
        //第二种方法：
        int m = prices.length;
        int[][] bp = new int[m + 1][n + 1];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= n; j++) {
                bp[i + 1][j] = bp[i][j]; 
            }
            for (int k = 1; k <= amounts[i]; k++) {
                for (int j = k * prices[i]; j <= n; j++) {
                    bp[i + 1][j] = Math.max(bp[i + 1][j], bp[i][j - k * prices[i]] + k * weight[i]);
                }
            }
        }
        return bp[m][n];
    }
}

/* 算法：第一种方法写起来更简洁，纯粹是0-1背包的扩展，难点在于j >= prices[i]，因为最少要拿一件
**       第二种方法空间复杂度更高 
** 时间复杂度O(V∑num[i])，空间复杂度 O(NV) */