public class Solution {
    /*
     * @param : A string
     * @param : A set of word
     * @return: the number of possible sentences.
     */
    public int wordBreak3(String s, Set<String> dict) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        String lowerS = s.toLowerCase();
        Set<String> lowerDict = new HashSet<>();
        for (String str : dict) {
            lowerDict.add(str.toLowerCase());
        }
        int n = lowerS.length();
        int[][] dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (lowerDict.contains(lowerS.substring(i, j + 1))) {
                    dp[i][j] = 1;
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int k = i; k < j; k++) {
                    dp[i][j] += dp[i][k] * dp[k + 1][j];
                }
            }
        }
        return dp[0][n - 1];
    }
}

/* 算法：动态规划
** 难点：dp[i][j] += ....., 一定要preprocess lower case */