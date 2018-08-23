public class Solution {
    /**
     * @param s: the maximum length of s is 1000
     * @return: the longest palindromic subsequence's length
     */
    boolean[][] flag;
    int[][] f;
    //用记忆化搜索 
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] sc = s.toCharArray();
        int n = sc.length;
        flag = new boolean[n][n];
        f = new int[n][n];
        
        return dfs(sc, 0, n - 1);
    } 
    
    private int dfs(char[] sc, int start, int end) {
        if (flag[start][end]) {
            return f[start][end];
        }
        if (start == end) {
            f[start][end] = 1;
            return 1;
        }
        if (start + 1 == end) {
            if (sc[start] == sc[end]) {
                f[start][end] = 2;
                return 2;
            }
            f[start][end] = 1;
            return 1;
        }
        dfs(sc, start + 1, end);
        dfs(sc, start, end - 1);
        dfs(sc, start + 1, end - 1);
        
        f[start][end] = Math.max(f[start + 1][end], f[start][end - 1]);
        if (sc[start] == sc[end]) {
            f[start][end] = Math.max(f[start][end], f[start + 1][end - 1] + 2);
        }
        flag[start][end] = true;
        return f[start][end];
    }
    // public int longestPalindromeSubseq(String s) {
    //     if (s == null || s.length() == 0) {
    //         return 0;
    //     }
    //     char[] sc = s.toCharArray();
    //     int n = sc.length;
        
    //     int[][] f = new int[n][n];
        
    //     for (int i = sc.length - 1; i >= 0; i--) {
    //         f[i][i] = 1;
    //         for (int j = i + 1; j < sc.length; j++) {
    //             if (sc[i] == sc[j]) {
    //                 f[i][j] = f[i + 1][j - 1] + 2;
    //             } else {
    //                 f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
    //             }
    //         }
    //     }
    //     return f[0][n - 1];
    // }
}

/* 算法：区间型动态规划，起点倒过来循环，终点正过来循环 
** 时间复杂度：O(n^2)
** 空间复杂度：O(n^2)*/