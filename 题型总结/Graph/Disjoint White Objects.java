/* In a 2D black image there are some disjoint white objects with arbitrary shapes, find the number of disjoint white objects in an efficient way.

By disjoint, it means there is no white pixels that can connect the two objects, there are four directions to move to a neighbor pixel (left, right, up, down).

Black is represented by 1’s and white is represented by 0’s.

Assumptions

The given image is represented by a integer matrix and all the values in the matrix are 0 or 1
The given matrix is not null
Examples

the given image is

    0  0  0  1

    1  0  1  1

    1  1  0  0

    0  1  0  0

there are 3 disjoint white objects. */
public class Solution {
  public int whiteObjects(int[][] matrix) {
      if (matrix == null || matrix.length == 0) {
          return 0;
      }
      int n = matrix.length;
      int m = matrix[0].length; 
      int count = 0;
    
      for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
              if (matrix[i][j] == 0) {
                  matrix[i][j] = 1;
                  helper(matrix, i, j);
                  count++;
              }
          }
      }
      return count;
  }
  
  private void helper(int[][] matrix, int x, int y) {
      Queue<Pair> queue = new LinkedList<>();
      int[] dx = {1, 0, -1, 0};
      int[] dy = {0, 1, 0, -1};
      int n = matrix.length, m = matrix[0].length;
      
      queue.offer(new Pair(x, y));
      matrix[x][y] = 1;
      while (!queue.isEmpty()) {
          Pair p = queue.poll();
          for (int i = 0; i < 4; i++) {
              int nx = p.x + dx[i];
              int ny = p.y + dy[i];
              if (nx >= 0 && nx < n && ny >= 0 && ny < m && matrix[nx][ny] == 0) {
                  queue.offer(new Pair(nx, ny));
                  matrix[nx][ny] = 1;
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
/* 算法：如果input直接是matrix, 那么unoin find的时间复杂度反而高至少是O(n^3), 
        但是bfs的时间复杂度最多就是O(n^2), 所以更好。union find适合于直接告诉你
        要union的两个东西[a, b], 不需要自己for循环去找
   时间复杂度：O(n^2)
   空间复杂度：O(n^2) due to queue
*/