class Solution {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<int[]>();
        }
        List<int[]> result = new ArrayList<>();
        int n = matrix.length;
        int m = matrix[0].length;
        Queue<Point> pQueue = new LinkedList<>();
        Queue<Point> aQueue = new LinkedList<>();
        boolean[][] pMatrix = new boolean[n][m];
        boolean[][] aMatrix = new boolean[n][m];
        
        //add row
        for (int i = 0; i < n; i++) {
            pMatrix[i][0] = true;
            pQueue.offer(new Point(i, 0));
            aMatrix[i][m - 1] = true;
            aQueue.offer(new Point(i, m - 1));
        }
        //add column
        for (int j = 0; j < m; j++) {
            pMatrix[0][j] = true;
            pQueue.offer(new Point(0, j));
            aMatrix[n - 1][j] = true;
            aQueue.offer(new Point(n - 1, j));
        }
        bfs(matrix, pMatrix, pQueue);
        bfs(matrix, aMatrix, aQueue);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (pMatrix[i][j] && aMatrix[i][j]) {
                    int[] arr = new int[2];
                    arr[0] = i;
                    arr[1] = j;
                    result.add(arr);
                } 
            }
        }
        return result;
    }
    
    private void bfs (int[][] matrix, boolean[][] visited, Queue<Point> queue) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[0].length 
                   && !visited[nx][ny] && matrix[nx][ny] >= matrix[p.x][p.y]) { //难点！！
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny));
                }
            }
        }
    }
}

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

/* 算法：经典bfs
** 难点：53行水从中间往旁边流意味着从外到里是增大的 
** 时间复杂度：O(n^2) */