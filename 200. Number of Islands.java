class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] flag = new boolean[n][m];
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1' && !flag[i][j]) {
                    bfs(grid, flag, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    
    private void bfs(char[][] grid, boolean[][] flag, int x, int y) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Coordinate> q = new LinkedList<>();
        
        q.offer(new Coordinate(x, y));
        flag[x][y] = true;
        while (!q.isEmpty()) {
            Coordinate c = q.poll();
            for (int i = 0; i < 4; i++) {
                Coordinate n = new Coordinate(c.x + dx[i], c.y + dy[i]);
                if (n.x >= 0 && n.x < grid.length && n.y >= 0 && n.y < grid[0].length 
                    && !flag[n.x][n.y] && grid[n.x][n.y] == '1') {
                    q.offer(n);
                    flag[n.x][n.y] = true;
                }
            }
        }
    }
}

class Coordinate {
    int x, y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

/* 算法：BFS比Union Find要容易写一些
** 时间复杂度：O(n^2) 
** 空间复杂度：O(n^2) 
** Union Find的时间复杂度要好一些，是O(1) */