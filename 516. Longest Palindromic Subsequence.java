class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]); //要么不包括i, 要么不包括j
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}

/* 算法：跟Longest Palindrome string的dp答案一样是区间版dp
** 时间复杂度：O(n^2) 
** 空间复杂度：O(n^2) */