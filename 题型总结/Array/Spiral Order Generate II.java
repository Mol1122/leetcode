/* Generate an M * N 2D array in spiral order clock-wise starting from the top left corner, using the numbers of 1, 2, 3, …, M * N in increasing order.

Assumptions

M >= 0, N >= 0
Examples

M = 3, N = 4, the generated matrix is

{ {1,  2,  3,  4}

  {10, 11, 12, 5},

  {9,  8,  7,  6} } */

public class Solution {
  public int[][] spiralGenerate(int m, int n) {
    int[][] results = new int[m][n];
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    int dir = 0;
    int x = 0, y = 0;
    boolean[][] visited = new boolean[m][n];
    int num = 1;

    for (int i = 0; i < m * n; i++) {
      results[x][y] = num;
      num++;
      visited[x][y] = true;
      int nx = x + dx[dir];
      int ny = y + dy[dir];

      if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny]) {
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
//time: O(n*m), space: O(n*m)