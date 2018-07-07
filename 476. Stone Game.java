public class Solution {
    /**
     * @param A: An integer array
     * @return: An integer
     */
    public int stoneGame(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        //initialize
        int n = A.length;
        int[][] f = new int[n][n];        
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            f[i][i] = 0;
        }
        
        //preparation
        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            sum[i][i] = A[i];
            for (int j = i + 1; j < n; j++) {
                sum[i][j] = sum[i][j - 1] + A[j];
            }
        }
        return search(A, 0, n - 1, f, visited, sum);
    }
    
    private int search(int[] A, int left, int right, int[][] f, boolean[][] visited, int[][] sum) {
        if (visited[left][right]) {
            return f[left][right];
        }
        if (left == right) {
            visited[left][right] = true;
            return f[left][right];
        }
        f[left][right] = Integer.MAX_VALUE;
        for (int k = left; k < right; k++) {
            f[left][right] = Math.min(f[left][right], 
                            search(A, left, k, f, visited, sum) + search (A, k + 1, right, f, visited, sum) + sum[left][right]);
        }
        visited[left][right] = true;
        return f[left][right];
    }
}

/* 算法：区间类dp, 从大往小了搜索。用到了记忆化搜索
**       state: f[i][j]表示第i个石子到第j个石子的最小花费
**       function: dp[i][j] = min(dp[i][k]+dp[k+1][j]+sum[i,j]) 对于所有k属于{i,j}
**       initialize: f[i][i] = 0;
**       answer: f[0][n - 1]
** 时间复杂度：O(n^3) 因为37行你要填满就是n^2,然后还要枚举切分点就是n所以一起是O(n^3) */