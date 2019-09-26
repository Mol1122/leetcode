/* Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. 
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:

Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed. */

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] memo = new int[n][m];
        
        int max = 1;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int length = dfs(matrix, i, j, memo, new boolean[n][m]);
                max = Math.max(max, length);
            }
        }
        return max;
    }
    
    private int dfs(int[][] matrix, int x, int y, int[][] memo, boolean[][] visited) {
        if (memo[x][y] != 0) {
            return memo[x][y];
        }
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int max = 1;
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx < 0 || nx >= matrix.length || ny < 0 || ny >= matrix[0].length || 
               matrix[nx][ny] <= matrix[x][y]) {
                continue;
            }
            int len = 1 + dfs(matrix, nx, ny, memo, visited);
            max = Math.max(max, len);
            
        }
        memo[x][y] = max;
        visited[x][y] = true;
        return max;
    }
}
//time: O(n * m), space: O(n * m)