class Solution {
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int[][] distance = new int[n][m]; //the distance from each 0 location to all buildings
        int[][] reach = new int[n][m]; //record the number of buildings this point can reach
        int numBuilding = 0; // the number of buildings
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    numBuilding++;
                    Queue<int[]> queue = new LinkedList<int[]>();
                    queue.offer(new int[]{i, j});
                    
                    boolean[][] visited = new boolean[n][m];
                    visited[i][j] = true;
                    
                    int level = 1;
                    
                    while (!queue.isEmpty()) {
                        int qSize = queue.size();
                        for (int k = 0; k < qSize; k++) { //难点: 这里因为要保证BFS，如果没有这个限定，那么每层增加的level不可控
                            int[] cn = queue.poll();
                            
                            for (int d = 0; d < 4; d++) {
                                int nx = cn[0] + dx[d];
                                int ny = cn[1] + dy[d];
                                if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == 0 && !visited[nx][ny]) {
                                    distance[nx][ny] += level;
                                    reach[nx][ny]++;
                                    visited[nx][ny] = true;
                                    queue.offer(new int[]{nx, ny});
                                }
                            }
                        }
                        level++;
                    }
                    
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0 && reach[i][j] == numBuilding) {
                    result = Math.min(result, distance[i][j]);
                }
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}

/* 算法：利用BFS去找出每个为0的点到所有building的距离，然后再循环找出最小值
** 难点：需要记录level的BFS必须要用qSize去限定循环
** 时间复杂度：O(n^2 * m^2) */