public class Solution {
    /**
     * @param grid: a 2D integer grid
     * @return: an integer
     */
    public int zombie(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int n = grid.length;
        int m = grid[0].length;
        Queue<Point> queue = new LinkedList<>();
        int people = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    people++;
                } else if (grid[i][j] == 1) {
                    queue.offer(new Point(i, j));
                }
            }
        }
        int steps = 0;
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                for (int j = 0; j < 4; j++) {
                    Point np = new Point(p.x + dx[j], p.y + dy[j]);
                    if (np.x >= 0 && np.x < n && np.y >= 0 && np.y < m && grid[np.x][np.y] == 0) {
                        grid[np.x][np.y] = 1;
                        queue.offer(np);
                        people--;
                    } 
                }
            }
            if (people == 0) {
                return steps;
            }
        }
        return -1;
    }
}

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

/* 算法：最短距离BFS
** 时间复杂度：O($people * n * m) 
** 难点: 利用people去计数*/