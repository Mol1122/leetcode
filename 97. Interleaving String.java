class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int n = s1.length();
        int m = s2.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        
        for (int i = 1; i <= n; i++) {
            if (s1.charAt(i - 1) == s3.charAt(i - 1) && dp[i - 1][0]) {
                dp[i][0] = true;
            }
        }
        for (int j = 1; j <= m; j++) {
            if (s2.charAt(j - 1) == s3.charAt(j - 1) && dp[0][j - 1]) {
                dp[0][j] = true;
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s3.charAt(i + j - 1) == s1.charAt(i - 1) && dp[i - 1][j] ||
                           s3.charAt(i + j - 1) == s2.charAt(j - 1) && dp[i][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[n][m];
     }
}

/* 算法：匹配类dp
        dp[i][j] = s1的前i个字符和s2的前j个字符是否能组合成s3的前i+j个字符
        function: dp[i][j] = s3.charAt(i + j - 1) == s1.charAt(i - 1) && dp[i - 1][j] ||
                             s3.charAt(i + j - 1) == s2.charAt(j - 1) && dp[i][j - 1]
        initialize: dp[0][0] = true, 没有s2,s1的前i个字符是否能组合成s3的前i个字符
        answer: dp[s1.length()][s2.length()]
    时间复杂度：O(nm)
        */