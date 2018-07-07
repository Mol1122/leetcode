public class Solution {
    /**
     * @param n: the money you have
     * @return: the minimum money you have to give
     */
    public int backPackX(int n) {
        int[] f = new int[n + 1];
        int[] prices = {150, 250, 350};
        
        for (int i = 0; i < 3; i++) {
            for (int j = prices[i]; j <= n; j++) {
                f[j] = Math.max(f[j], f[j - prices[i]] + prices[i]);
            }
        }
        return n - f[n];
    }
}

/* 算法：完全背包的写法，没有什么特别的地方 */