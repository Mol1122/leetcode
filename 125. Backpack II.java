public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
     /*   int n = A.length;
        int[][] backpack = new int[2][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                backpack[(i + 1) % 2][j] = backpack[i % 2][j];
                if (j >= A[i]) {
                    backpack[(i + 1) % 2][j] = Math.max(backpack[(i + 1) % 2][j], backpack[i % 2][j - A[i]] + V[i]);
                }
            }
        }
        return backpack[n % 2][m];  */
        
        int n = A.length;
        int[] f = new int[m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = m; j >= A[i]; j--) {
                f[j] = Math.max(f[j], f[j - A[i]] + V[i]);
            }
        }
        return f[m];
    }
}

/* 利用滚动数组优化：
** 时间复杂度：O(N*V)
** 空间复杂度：O(v) 
**
** 利用一维数组：
** 在每个i初始， f[j]表示backpack[i - 1][j]. 由于第二层循环倒序，所以 f[j - cap[i]] 未被更新，
   此时它代表 backpack[i - 1][j - cap[i]]. 在第 i 层循环末 f[j] 存的相当于 backpack[i][j] 的值。
*/