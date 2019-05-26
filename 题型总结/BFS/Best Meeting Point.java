/* A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6. */
public class Solution {
  public int minTotalDistance(int[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
        return 0;
    }
    int n = grid.length;
    int m = grid[0].length;
    int[][] distance = new int[n][m];
    int[][] house = new int[n][m];
    int houseCount = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (grid[i][j] == 1) {
                houseCount++;
                boolean[][] visited = new boolean[n][m];
                bfs(grid, distance, visited, house, i, j);
            }
        }
    }
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            min = Math.min(min, distance[i][j]);
        }
    }
    return min;
  }

   private void bfs(int[][] grid, int[][] distance, boolean[][] visited, int[][] house,
                   int x, int y) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(x, y));
        visited[x][y] = true;

        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair p = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];
                    if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length &&
                        !visited[nx][ny]) {
                            distance[nx][ny] += step;
                            visited[nx][ny] = true;
                            house[nx][ny]++;
                            queue.offer(new Pair(nx, ny));
                        }
                }
            }
            step++;
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
//time: O(n^2), space: O(n^2)
//难点：只是两个或者多个people meet, 并不一定是所有人都要meet, 所以不需要house去记录能有多少个people能走到