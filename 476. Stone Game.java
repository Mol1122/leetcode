public class Solution {
    /**
     * @param A: An integer array
     * @return: An integer
     */
    public int stoneGame(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[][] f = new int[n][n];
        boolean[][] flag = new boolean[n][n];
        
        //preparation
        int[][] sums = new int[n][n];
        for (int i = 0; i < n; i++) {
            sums[i][i] = A[i];
            for (int j = i + 1; j < n; j++) {
                sums[i][j] = sums[i][j - 1] +  A[j];
            }
        }
        
        //initialization
        for (int i = 0; i < n; i++) {
            f[i][i] = 0;
        }
        
        return search(A, 0, n - 1, f, flag, sums);
    }
    
    private int search(int[] A, int left, int right, int[][] f, boolean[][] flag, int[][] sums) {
        if (flag[left][right]) {
            return f[left][right];
        }
        if (left == right) {
            flag[left][right] = true;
            return f[left][right];
        }
        f[left][right] = Integer.MAX_VALUE;
        for (int k = left; k < right; k++) {
            f[left][right] = Math.min(f[left][right], search(A, left, k, f, flag, sums) + 
                                                      search(A, k + 1, right, f, flag, sums) +
                                                      sums[left][right]);
        }
        flag[left][right] = true;
        return f[left][right];
    }
}