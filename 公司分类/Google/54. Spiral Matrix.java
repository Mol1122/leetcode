public class Solution {
  public List<Integer> spiral(int[][] matrix) {
      List<Integer> results = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return results;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int dir = 0;
        int x = 0, y = 0;
        boolean[][] visited = new boolean[n][m];
        
        for (int i = 0; i < n * m; i++) {
            results.add(matrix[x][y]);
            visited[x][y] = true;
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] == true) {
                dir = (dir + 1) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }
            x = nx;
            y = ny;
        }
        return results;
  }
}
