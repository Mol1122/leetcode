/* 
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).

Return the number of distinct islands.
 */

 class Solution {
    public int numDistinctIslands2(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        Set<String> results = new HashSet<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    List<Point> list = bfs(grid, i, j, visited);
                    results.add(norm(list));
                }
            }
        }
        return results.size();
    }
    
    private String norm(List<Point> list) {
        List<Point>[] comb = new List[8]; //stores the combination of relection and rotation
        int[][] reflect = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}}; //fix x, y; fix x, reflect y; reflect x, fix y; etc.
        
        for (int i = 0; i < 4; i++) {
            comb[i] = new ArrayList<Point>(); 
            comb[i + 4] = new ArrayList<Point>();
            for (Point p : list) {
                comb[i].add(new Point(p.x * reflect[i][0], p.y * reflect[i][1])); //reflection
                comb[i + 4].add(new Point(p.y * reflect[i][1], p.x * reflect[i][0])); //rotation
            }
        }
        for (int i = 0; i < 8; i++) {
            Collections.sort(comb[i], new Comparator<Point>(){
                public int compare(Point a, Point b) {
                    if (a.x == b.x) {
                        return a.y - b.y;
                    }
                    return a.x - b.x;
                }
            });
        }
        String[] strs = new String[8];
        for (int i = 0; i < 8; i++) { //get the relative position of each shape
            StringBuilder sb = new StringBuilder();
            int x0 = comb[i].get(0).x, y0 = comb[i].get(0).y;
            for (Point p : comb[i]) {
                sb.append(p.x - x0);
                sb.append(',');
                sb.append(p.y - y0);
                sb.append('!');
            }
            strs[i] = sb.toString();
        }
        Arrays.sort(strs);
        return strs[0]; //only return the representative
    }
    
    private List<Point> bfs(int[][] grid, int x, int y, boolean[][] visited) {
        List<Point> results = new ArrayList<>();
        Queue<Point> queue = new LinkedList<>();
        
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        queue.offer(new Point(x, y));
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            results.add(curr);
            
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                Point next = new Point(nx, ny);
                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && 
                    grid[nx][ny] == 1 && !visited[nx][ny]) {
                    queue.offer(next);
                    visited[nx][ny] = true;
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
// time: O(n^2 log(n^2))