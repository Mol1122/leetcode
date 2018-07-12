class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        
        //both s, p empty
        dp[0][0] = true;
        
        //s not empty, p empty
        for (int i = 1; i <= n; i++) {
            dp[i][0] = false;
        }
        
        //s emtpy, p not empty
        for (int j = 1; j <= m; j++) {
            dp[0][1] = false;
            if (j >= 2) {
                dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
            }
        }
        //s, p not empty
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char c = p.charAt(j - 1);
                if (c != '*') {
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == c || c == '.');
                } else {
                    if (j == 1) {
                        dp[i][j] = false;
                        continue;
                    }
                    dp[i][j] = dp[i][j - 2] || (dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.' ));
                }
            }
        }
        return dp[n][m];
    }
}

/* 算法：动态规划
** 时间复杂度： O(nm)*/