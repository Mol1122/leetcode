public class Solution {
    /**
     * @param A: An integer matrix
     * @return: an integer
     */
    int[][] dp;
    int[][] flag;
    public int longestIncreasingContinuousSubsequenceII(int[][] A) {
        // write your code here
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int m = A[0].length;
        dp = new int[n][m];
        flag = new int[n][m];
        int ans = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = search(i, j, A);
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
    
    private int search(int x, int y, int[][] A) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        if (flag[x][y] != 0) {
            return dp[x][y];
        }
        
        int ans = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < A.length && ny >= 0 && ny < A[0].length) {
                if (A[x][y] > A[nx][ny]) {
                    ans = Math.max(ans, search(nx, ny, A) + 1);
                } 
            }
        }
        dp[x][y] = ans;
        flag[x][y] = 1;
        return ans;
    }
}

/* 算法：外壳是dfs,内核是记忆画搜索，记忆画搜索实际就是动态规划 
** 时间复杂度：O(n*m). 在没有用记忆画搜索前是O(n^2*m^2) */