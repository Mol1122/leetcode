public class Solution {
    /**
     * @param s1: A string
     * @param s2: A string
     * @param s3: A string
     * @return: Determine whether s3 is formed by interleaving of s1 and s2
     */
    public boolean isInterleave(String ss1, String ss2, String ss3) {
        if (ss1 == null || ss2 == null) {
            return false;
        }
        char[] s1 = ss1.toCharArray();
        char[] s2 = ss2.toCharArray();
        char[] s3 = ss3.toCharArray();
        int n = s1.length;
        int m = s2.length;
        if (n + m != s3.length) {
            return false;
        }
        boolean[][] f = new boolean[n + 1][m + 1];    
        // for (int i = 0; i <= n; i++) { //更简洁的写法
        //     for (int j = 0; j <= m; j++) {
        //         if (i == 0 && j == 0) {
        //             f[0][0] = true;
        //             continue;
        //         }
        //         f[i][j] = false;
        //         if (i > 0) {
        //             f[i][j] |= f[i - 1][j] && s1[i - 1] == s3[i + j - 1];
        //         }
        //         if (j > 0) {
        //             f[i][j] |= f[i][j - 1] && s2[j - 1] == s3[i + j - 1];
        //         }
        //     }
        // }
        
        //更加intuitive的写法
        f[0][0] = true;
        for (int i = 0; i <= n; i++) {
            f[i][0] = ss1.substring(0, i).equals(ss3.substring(0, i));
        }
        for (int j = 0; j <= m; j++) {
            f[0][j] = ss2.substring(0, j).equals(ss3.substring(0, j));
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = f[i - 1][j] && s1[i - 1] == s3[i + j - 1] ||
                          f[i][j - 1] && s2[j - 1] == s3[i + j - 1];
            }
        }
        return f[n][m];
    }
}

/* 算法：双序列型动态规划
         f[i][j] = X的前i+j个字符是否由A的前i个字符和B的前j个字符交错形成
    转移方程：f[i][j] = f[i - 1][j] && X[i + j - 1] == A[i - 1] OR
                        f[i][j - 1] && X[i + j - 1] == B[j - 1]
    难点：A或者B有可能是""   
    时间复杂度：O(nm)
    空间复杂度：O(nm), 可以优化至O(n) */