public class Solution {
    /**
     * @param s: A string
     * @return: An integer
     */
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] sc = s.toCharArray();
        boolean[][] isPalin = getPalindrome(sc);
        
        int n = sc.length;
        int[] f = new int[n + 1];
        f[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            //枚举最后一段回文串的起点
            f[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (isPalin[j][i - 1]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }
        return f[n] - 1;
    }
    
    private boolean[][] getPalindrome(char[] sc) {
        boolean[][] isPalin = new boolean[sc.length][sc.length];
        
        for (int i = 0; i < sc.length; i++) {
            isPalin[i][i] = true;
        }
        
        for (int i = 0; i < sc.length - 1; i++) {
            isPalin[i][i + 1] = sc[i] == sc[i + 1];
        }
        
        for (int i = sc.length - 3; i >= 0; i--) {
            for (int j = i + 2; j < sc.length; j++) {
                isPalin[i][j] = isPalin[i + 1][j - 1] && sc[i] == sc[j];
            }
        }
        return isPalin;
    }
}

/* 算法：划分型动态规划，做法类似于序列型
         f[i] = 前i个字符都是回文串的最小划分次数
         分析方法就是看最后一步->最后一段是回文串的最小cut次数，但是并不知道起点j是什么，所以要枚举
                       子问题 -> 前一段的回文串个数加上最后的一个回文串个数1*/