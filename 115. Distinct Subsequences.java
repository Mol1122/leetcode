class Solution {
    public int numDistinct(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i -1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[n][m];
    }
}
/* 算法：匹配类dp
         state: dp[i][j]表示S的不同的子序列中T出现的个数
         function: 不想等的时候，dp[i][j] = dp[i - 1][j]表示跟S少一个字母在t中出现的次数一样
    时间复杂度：O(nm) */