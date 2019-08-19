/* Given a gym with k pieces of equipment and some obstacles.  We bought a chair and wanted to put this chair into the gym such that  the sum of the shortest path cost from the chair to the k pieces of equipment is minimal. The gym is represented by a char matrix, ‘E’ denotes a cell with equipment, ‘O’ denotes a cell with an obstacle, 'C' denotes a cell without any equipment or obstacle. You can only move to neighboring cells (left, right, up, down) if the neighboring cell is not an obstacle. The cost of moving from one cell to its neighbor is 1. You can not put the chair on a cell with equipment or obstacle.

Assumptions

There is at least one equipment in the gym
The given gym is represented by a char matrix of size M * N, where M >= 1 and N >= 1, it is guaranteed to be not null
It is guaranteed that each 'C' cell is reachable from all 'E' cells.
If there does not exist such place to put the chair, just return {-1, -1}
Examples

{ { 'E', 'O', 'C' },

  {  'C', 'E',  'C' },

  {  'C',  'C',  'C' } }

we should put the chair at (1, 0), so that the sum of cost from the chair to the two equipment is 1 + 1 = 2, which is minimal. */

public class Solution {
  public List<Integer> putChair(char[][] grid) {
        List<Integer> result = Arrays.asList(-1, -1);
        if (grid == null || grid.length == 0) {
            return result;
        }
        int n = grid.length;
        int m = grid[0].length;
        int equipCount = 0;
        int[][] distance = new int[n][m];
        int[][] equip = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'E') { //里面只会被执行k次， k = #'E'
                    equipCount++;
                    boolean[][] visited = new boolean[n][m];
                    bfs(grid, distance, visited, equip, i, j);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'C' && equip[i][j] == equipCount && distance[i][j] < min) {
                    min = Math.min(min, distance[i][j]);
                    result = Arrays.asList(i, j);
                }
            }
        }

        return result;

    }

    private void bfs(char[][] grid, int[][] distance, boolean[][] visited, int[][] equip, int x, int y) {
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
                            grid[nx][ny] != 'O' && !visited[nx][ny]) {
                        distance[nx][ny] += step;
                        visited[nx][ny] = true;
                        equip[nx][ny]++;
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
//time: O(k*mn）, bfs最多被叫了k次，bfs里最多poll mn次
//space: O(nm)
//难点：只有'O'不能走