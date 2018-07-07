public class Solution {
    /**
     * @param A: an integer array
     * @param V: an integer array
     * @param m: An integer
     * @return: an array
     */
    public int backPackIII(int[] A, int[] V, int m) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[] f = new int[m + 1];
        
        for (int i = 0; i < n; i++) {
            for (int j = A[i]; j <= m; j++) {
                f[j] = Math.max(f[j], f[j - A[i]] + V[i]);
            }
        }
        return f[m];
    }
}

/* 算法：典型的完全背包问题
** 时间复杂度：O(nm)
** 空间复杂度：O(m) */