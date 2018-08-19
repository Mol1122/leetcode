public class Solution {
    /**
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] sc = s.toCharArray();
        int n = sc.length;
        
        int[] f = new int[n + 1];
        f[0] = 1; //空串只有一种解密方式
        
        for (int i = 1; i <= n; i++) {
            f[i] = 0;
            if (sc[i - 1] > '0') {
                f[i] += f[i - 1];
            }
            
            if (i >= 2) {
                int val = (sc[i - 2] - '0') * 10 + sc[i - 1] - '0';
                if (val >= 10 && val <= 26) {
                    f[i] += f[i - 2];
                }
            }
        }
        return f[n];
    }
}

/* 算法：序列型动态规划
** 时间复杂度：O(n)
** 空间复杂度：O(n) */