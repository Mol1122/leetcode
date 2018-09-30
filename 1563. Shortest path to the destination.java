public class Solution {
    /**
     * @param targetMap: 
     * @return: nothing
     */
    public int shortestPath(int[][] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int n = A.length;
        int m = A[0].length;
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        qx.offer(0);
        qy.offer(0);
        visited[0][0] = true;
        
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        int step = 0;
        while (!qx.isEmpty()) {
            int size = qx.size();
            for (int i = 0; i < size; i++) {
                int cx = qx.poll();
                int cy = qy.poll();
                
                if (A[cx][cy] == 2) {
                    return step;
                }
                
                for (int j = 0; j < 4; j++) {
                    int nx = cx + dx[j];
                    int ny = cy + dy[j];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && A[nx][ny] != 1) {
                        qx.offer(nx);
                        qy.offer(ny);
                        visited[nx][ny] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
}