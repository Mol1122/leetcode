/* Given a non-negative integer 2D array representing the heights of bars in a matrix. Suppose each bar has length and width of 1. Find the largest amount of water that can be trapped in the matrix. The water can flow into a neighboring bar if the neighboring bar's height is smaller than the water's height. Each bar has 4 neighboring bars to the left, right, up and down side.

Assumptions

The given matrix is not null and has size of M * N, where M > 0 and N > 0, all the values are non-negative integers in the matrix.
Examples

{ { 2, 3, 4, 2 },

  { 3, 1, 2, 3 },

  { 4, 3, 5, 4 } }

the amount of water can be trapped is 3, 

at position (1, 1) there is 2 units of water trapped,

at position (1, 2) there is 1 unit of water trapped.

 */
 
public class Solution {
  public int maxTrapped(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
        return 0;
    }
    int n = matrix.length;
    int m = matrix[0].length;
    Queue<Pair> minheap = new PriorityQueue<>(new Comparator<Pair>() {
        public int compare(Pair a, Pair b) {
            return a.height - b.height;
        }
    });
    boolean[][] visited = new boolean[n][m];
    for (int i = 0; i < n; i++) {
        minheap.offer(new Pair(i, 0, matrix[i][0]));
        visited[i][0] = true;
        minheap.offer(new Pair(i, m - 1, matrix[i][m - 1]));
        visited[i][m - 1] = true;
    }
    for (int j = 0; j < m; j++) {
        minheap.offer(new Pair(0, j, matrix[0][j]));
        visited[0][j] = true;
        minheap.offer(new Pair(n - 1, j, matrix[n - 1][j]));
        visited[n - 1][j] = true;
    }
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    int sum = 0;
    while (!minheap.isEmpty()) {
        Pair p = minheap.poll();
        for (int i = 0; i < 4; i++) {
            int nx = p.x + dx[i];
            int ny = p.y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                minheap.offer(new Pair(nx, ny, Math.max(p.height, matrix[nx][ny])));
                visited[nx][ny] = true;
                sum += Math.max(0, p.height - matrix[nx][ny]);
            }
        }
    }
    return sum;
  }
}

class Pair {
    int x, y, height;
    public Pair(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }
}
//time: O(nmlognm), space: O(nm)