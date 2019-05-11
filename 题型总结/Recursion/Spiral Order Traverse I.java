public class Solution {
  public List<Integer> spiral(int[][] matrix) {
      //recursion的方法并不好，容易stack overflow, 而且必须为正方形，不够general
 /*     List<Integer> results = new ArrayList<>();
      if (matrix == null || matrix.length == 0) {
          return results;
      }
      helper(matrix, 0, matrix.length, results);
      return results;  */
      List<Integer> results = new ArrayList<>();
      if (matrix == null || matrix.length == 0) {
          return results;
      }
      int n = matrix.length;
      int m = matrix[0].length;
      int[] dx = {0, 1, 0, -1};
      int[] dy = {1, 0, -1, 0};
      int x = 0, y = 0, d = 0;
      boolean[][] visited = new boolean[n][m];
    
      for (int i = 0; i < n * m; i++) {
         results.add(matrix[x][y]);
         visited[x][y] = true;
         int nx = x + dx[d];
         int ny = y + dy[d];
        
          if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) {
              d = (d + 1) % 4;
              nx = x + dx[d];
              ny = y + dy[d];
          }
          x = nx;
          y = ny;
      }
      return results;  
  }
  
  private void helper(int[][] matrix, int offset, int size, List<Integer> results) {
      if (size == 1) {
          results.add(matrix[0 + offset][0 + offset]);
          return;
      }
      int n = matrix.length;
      for (int i = 0; i < size - 1; i++) {
          results.add(matrix[0 + offset][i + offset]);
      }
      for (int i = 0; i < size - 1; i++) {
          results.add(matrix[i + offset][n - 1 - offset]);
      }
      for (int i = size - 1; i >= 0; i--) {
          results.add(matrix[n - 1 - offset][i + offset]);
      }
      for (int i = size - 1; i >= 0; i--) {
          results.add(matrix[i + offset][0 + offset]);
      }
      helper(matrix, offset + 1, size - 2, results);
  }
}
