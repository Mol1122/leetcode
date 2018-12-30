class Solution {
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        boolean[][] isPalindrome = getPalindrome(s);
        
        int[] f = new int[n + 1]; //f[i] = 前i个字符能划分成几个palindrome
        f[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) { //枚举最后一段回文串的起点
                if (isPalindrome[j][i - 1]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }
        return f[n] - 1; //因为问的是有多少cut
    }
    
    private boolean[][] getPalindrome(String s) {
        int n = s.length();
        boolean[][] isPalin = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
            isPalin[i][i] = true;
        }
        
        for (int i = 0; i < n - 1; i++) {
            isPalin[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                isPalin[i][j] = isPalin[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            }
        }
        return isPalin;
    }
}

/* 划分型动态规划，f[i] = 前i个字符能划分成几个palindrome
** 时间复杂度：O(n^2)
** 空间复杂度：O(n^2) */