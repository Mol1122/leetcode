public class Solution {
    /**
     * @param A: A string
     * @param B: A string
     * @return: The length of longest common subsequence of A and B
     */
    public int longestCommonSubsequence(String A, String B) {
        if (A == null || B == null || A.length() == 0 || B.length() == 0) {
            return 0;
        }
        int n = A.length();
        int m = B.length();
        int[][] f = new int[n + 1][m + 1]; //A的前i个字符与B的前j个字符最大的匹配字符数
        
        //遍历A和B的前i, j个字符
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                //不包含A的最后一个或者不包含B的最后一个字符
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]); 
                //包含A,B的最后一个字符
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);
                }
            }
        }
        return f[n][m];
    }
}

/* 算法：匹配类dp = 双序列型动态规划
         state: f[i][j] = A的前i个字符与B的前j个字符最大的匹配字符数
         function：f[i][j] = max(dp[i - 1][j], dp[i][j - 1]) -> 如果不相等
                            = dp[i - 1][j - 1] + 1 ->如果相等
        initialize:dp[0][0] = 0
    时间复杂度：O(nm) */