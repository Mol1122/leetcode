public class Solution {
    /**
     * @param n: the value from 1 - n
     * @param value: the value of coins
     * @param amount: the number of coins
     * @return: how many different value
     */
    public int backPackVIII(int n, int[] value, int[] amount) {
        int m = value.length;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        int result = 0;
        
        for (int i = 0; i < m; i++) {
            int[] count = new int[n + 1]; //空间换时间
            for (int j = value[i]; j <= n; j++) {
                if (!dp[j] && dp[j - value[i]] && count[j - value[i]] < amount[i]) {
                    count[j] = count[j - value[i]] + 1;
                    result++;
                    dp[j] = true;
                }
            }
        }
        return result;
    }
}

/* 算法：没有特别好的想法，背下来 */