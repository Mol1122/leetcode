class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0][0];
        }
        int n = matrix.length;
        int m = matrix[0].length;
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    qx.offer(i);
                    qy.offer(j);
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        while (!qx.isEmpty()) {
            int cx = qx.poll();
            int cy = qy.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && matrix[nx][ny] > matrix[cx][cy] + 1) {
                    matrix[nx][ny] = matrix[cx][cy] + 1;
                    qx.offer(nx);
                    qy.offer(ny);
                }
            }
        }
        return matrix;
    }
}

/* 算法：典型BFS
** 技巧：matrix[i] == 1, 另它等于Integer.MAX_VALUE,这样只要visit的value比之前大就说明需要update
** 时间复杂度：O(n * m) 
** 空间复杂度：O(n) */