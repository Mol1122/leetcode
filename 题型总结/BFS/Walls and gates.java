/* You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as 
you may assume that the distance to a gate is less than 2147483647.

Fill each empty room with the distance to its nearest gate. If it is impossible to reach a 
gate, it should be filled with INF.

For example, given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4 */
public class Solution {
  public int[][] wallsAndGates(int[][] rooms) {
    if (rooms == null || rooms.length == 0) {
        return rooms;
    }
    int n = rooms.length;
    int m = rooms[0].length;
    Queue<Pair> queue = new LinkedList<>();
    boolean[][] visited = new boolean[n][m];

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (rooms[i][j] == 0) {
                queue.offer(new Pair(i, j));
                visited[i][j] = true;
            }
        }
    }
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    int step = 1;
    while (!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Pair p = queue.poll();
            for (int j = 0; j < 4; j++) {
                int nx = p.x + dx[j];
                int ny = p.y + dy[j];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && 
                    rooms[nx][ny] != -1 && !visited[nx][ny]) {
                    queue.offer(new Pair(nx, ny));
                    visited[nx][ny] = true;
                    rooms[nx][ny] = step;
                }
            }
        }
        step++;
    }
    return rooms;
  }
}

class Pair {
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
//time: O(n^2), space: O(n^2)