public class Solution {
    /**
     * @param map: the map
     * @return: can you reach the endpoint
     */
    public boolean reachEndpoint(int[][] map) {
        if (map == null || map.length == 0) {
            return false;
        }
        int n = map.length;
        int m = map[0].length;
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        queue.offer(new Point(0, 0));
        visited[0][0] = true;
        
        
        
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            //System.out.println("Point = [" + p.x + ", " + p.y + "]");
            for (int i = 0; i < 4; i++) {
                Point np = new Point(p.x + dx[i], p.y + dy[i]);
                if (np.x >= 0 && np.x < n && np.y >= 0 && np.y < m && !visited[np.x][np.y] && map[np.x][np.y] != 0) {
                    if (map[np.x][np.y] == 9) {
                        return true;
                    }
                    queue.offer(np);
                    visited[np.x][np.y] = true;
                }
            }
        }
        return false;
    }
}

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}