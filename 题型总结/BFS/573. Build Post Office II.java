public class Solution {
    /**
     * @param grid: a 2D grid
     * @return: An integer
     */
    final int HOUSE = 1;
    final int EMPTY = 0;
    final int WALL = 2;
    int[][] grid;
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        this.grid = grid;
        int n = grid.length;
        int m = grid[0].length;
        int[][] distances = new int[n][m];
        int[][] visitedTimes = new int[n][m];
        List<Point> houses = getPoints(HOUSE);
        
        for (Point house : houses) {
            bfs(house, distances, visitedTimes);
        }
        int max = Integer.MAX_VALUE;
        for (Point e : getPoints(EMPTY)) {
            if (visitedTimes[e.x][e.y] == houses.size()) {
                max = Math.min(max, distances[e.x][e.y]);
            }
        }
        if (max == Integer.MAX_VALUE) { //易漏
            return -1;
        }
        return max;
        
    }
    
    private void bfs(Point start, int[][] distances, int[][] visitedTimes) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<Point> queue = new LinkedList<>();
        boolean[][] hash = new boolean[n][m];
        queue.offer(start);
        hash[start.x][start.y] = true;
        int steps = 0;
        
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                for (int j = 0; j < 4; j++) {
                    Point np = new Point(p.x + dx[j], p.y + dy[j]);
                    if (np.x >= 0 && np.x < n && np.y >= 0 && np.y < m && 
                        grid[np.x][np.y] == EMPTY && !hash[np.x][np.y]) {
                        queue.offer(np);
                        hash[np.x][np.y] = true;
                        visitedTimes[np.x][np.y]++;
                        distances[np.x][np.y] += steps;
                    }
                }
            }
        }
    }
    
    private List<Point> getPoints(int type) {
        int n = grid.length;
        int m = grid[0].length;
        List<Point> results = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == type) {
                    results.add(new Point(i, j));
                }
            }
        }
        return results;
    }
}

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

/* 算法：最短距离的BFS,矩阵。
** 难点：getHouses,分别求每个house到每个点的距离，然后多少个房子能到这个点；易漏最后盘算是不是Integer.MAX_VALUE
** 时间复杂度：O(n^3) */