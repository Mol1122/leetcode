class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0) {
            return 0;
        }
        int n = heightMap.length;
        int m = heightMap[0].length;
        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> a.h - b.h);
        int[][] visited = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            pq.offer(new Cell(i, 0, heightMap[i][0]));
            pq.offer(new Cell(i, m - 1, heightMap[i][m - 1]));
            visited[i][0] = 1;
            visited[i][m - 1] = 1;
        }
        for (int j = 0; j < m; j++) {
            pq.offer(new Cell(0, j, heightMap[0][j]));
            pq.offer(new Cell(n - 1, j, heightMap[n - 1][j]));
            visited[0][j] = 1;
            visited[n - 1][j] = 1;
        }
        int result = 0;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        while (!pq.isEmpty()) {
            Cell c = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && visited[nx][ny] == 0) {
                    visited[nx][ny] = 1;
                    pq.offer(new Cell(nx, ny, Math.max(c.h, heightMap[nx][ny])));
                    result += Math.max(0, c.h - heightMap[nx][ny]);
                }
            }
        }
        return result;
    }
}

class Cell {
    int x, y, h;
    public Cell(int x, int y, int h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }
}

/* 算法：跟一维的思路一样，从四周往里灌水，用heap去track最低点
** 难点：33行，在更新高度的时候，那个高选哪个 */