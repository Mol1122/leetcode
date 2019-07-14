/* Generate an N * N 2D array in spiral order clock-wise starting from the top left corner, using the numbers of 1, 2, 3, …, N * N in increasing order.

Assumptions

N >= 0
Examples

N = 3, the generated matrix is

{ {1,  2,  3}

  {8,  9,  4},

  {7,  6,  5} } */

public class Solution {
  public int[][] spiralGenerate(int n) {
    if (n <= 0) {
      return new int[0][0];
    }
    int num = 1;
    int[][] results = new int[n][n];
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    int dir = 0;
    int x = 0, y = 0;
    boolean[][] visited = new boolean[n][n];

    for (int i = 0; i < n * n; i++) {
      results[x][y] = num;
      visited[x][y] = true;
      num++;
      int nx = x + dx[dir];
      int ny = y + dy[dir];

      if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) {
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
//time: O(n^2), space: O(n^2)