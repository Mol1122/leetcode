public class Solution {
    /**
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        final int INF = 2147483647;
        
        int n = rooms.length;
        int m = rooms[0].length;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == 0) {
                    qx.offer(i);
                    qy.offer(j);
                }
            }
        }
        int dist = 0;
        while (!qx.isEmpty()) {
            dist++;
            int size = qx.size();
            for (int j = 0; j < size; j++) {
                int cx = qx.poll();
                int cy = qy.poll();
            
                for (int i = 0; i < 4; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && 
                        rooms[nx][ny] == INF) {
                        qx.offer(nx);
                        qy.offer(ny);
                        rooms[nx][ny] = dist;
                    }
                }
            }
            
        }
    }
}