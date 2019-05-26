/* Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water. */

public class Solution {
  public int numIslands(char[][] grid) {
      if (grid == null || grid.length == 0) {
          return 0;
      }
      int n = grid.length;
      int m = grid[0].length;
      int count = 0;
    
      for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
              if (grid[i][j] == '1') {
                  helper(grid, i, j);
                  count++;
              }
          }
      }
      return count;
  }
  
  private void helper(char[][] grid, int x, int y) {
      Queue<Pair> queue = new LinkedList<>();
      int[] dx = {1, 0, -1, 0};
      int[] dy = {0, 1, 0, -1};
      queue.offer(new Pair(x, y));
    
      while (!queue.isEmpty()) {
          Pair p = queue.poll();
          for (int i = 0; i < 4; i++) {
              int nx = p.x + dx[i];
              int ny = p.y + dy[i];
              if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == '1') {
                  queue.offer(new Pair(nx, ny));
                  grid[nx][ny] = '0';
              }
          }
      }
  }
}

class Pair {
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
//time: O(n * m), space: (n * m)
