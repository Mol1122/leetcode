class Solution {
    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        Queue<Pair> minheap = new PriorityQueue<>(new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.height - b.height;
            }
        });
        int n = heights.length;
        int m = heights[0].length;
        boolean[][] visited = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            minheap.offer(new Pair(i, 0, heights[i][0]));
            minheap.offer(new Pair(i, m - 1, heights[i][m - 1]));
            visited[i][0] = true;
            visited[i][m - 1] = true;
        }
        for (int j = 0; j < m; j++) {
            minheap.offer(new Pair(0, j, heights[0][j]));
            minheap.offer(new Pair(n - 1, j, heights[n - 1][j]));
            visited[0][j] = true;
            visited[n - 1][j] = true;
        }
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int sum = 0;
        
        while (!minheap.isEmpty()) {
            Pair curr = minheap.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) {
                    continue;
                }
                minheap.offer(new Pair(nx, ny, Math.max(curr.height, heights[nx][ny])));
                sum += Math.max(0, curr.height - heights[nx][ny]);
                visited[nx][ny] = true;
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

/* 时间复杂度：O(mn * log(m + n))
** 空间复杂度：O(n^2) */