class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, maxDFS(grid, i, j, 0));
                }
            }
        }
        return max;
    }
    
    private int maxDFS(int[][] grid, int x, int y, int count) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        if (grid[x][y] == 1) {
            grid[x][y] = 0;
            count = 0;
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == 1) {
                    count += maxDFS(grid, nx, ny, count);
                } else {
                    count += 0;
                }
            }
        }
        return count + 1;
    }
}

/* 算法：标准DFS写法，注意一旦visit过，把1改为0
** 时间复杂度：O(n^3) */