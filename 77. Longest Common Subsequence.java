public class Solution {
    /**
     * @param A: A string
     * @param B: A string
     * @return: The length of longest common subsequence of A and B
     */
    public int longestCommonSubsequence(String A, String B) {
        int n = A.length();
        int m = B.length();
        int[][] dp = new int[n + 1][m + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp[n][m];
    }
}

/* 算法：匹配类dp
         state: dp[i][j] = A的前i个字符与B的前j个字符最大的匹配字符数
         function：dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]) -> 如果不相等
                            = dp[i - 1][j - 1] + 1 ->如果相等
        initialize:dp[0][0] = 0
    时间复杂度：O(nm) */