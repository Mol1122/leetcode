/* You are given an m x n grid grid of values 0, 1, or 2, where:

each 0 marks an empty land that you can pass by freely,
each 1 marks a building that you cannot pass through, and
each 2 marks an obstacle that you cannot pass through.
You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. You can only move up, down, left, and right.

Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.

The total travel distance is the sum of the distances between the houses of the friends and the meeting point.

The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

 

Example 1:


Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
Output: 7
Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
So return 7.
Example 2:

Input: grid = [[1,0]]
Output: 1
Example 3:

Input: grid = [[1]]
Output: -1               */

//Method 1
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


//Method 2
class Solution {
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        
        int n = grid.length;
        int m = grid[0].length;
        int[][] cost = new int[n][m];
        int[][] house = new int[n][m];
        int min = Integer.MAX_VALUE;
        int buildings = 0;
              
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    buildings++;
                    bfs(grid, n, m, i, j, cost, house);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (house[i][j] == buildings) {
                    min = Math.min(min, cost[i][j]);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private void bfs(int[][] grid, int n, int m, int x, int y, int[][] distances, int[][] houses) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        qx.offer(x);
        qy.offer(y);
        visited[x][y] = true;
        
        int step = 1;
        while (!qx.isEmpty()) {
            int size = qx.size();
            for (int i = 0; i < size; i++) {
                int cx = qx.poll();
                int cy = qy.poll();
                
                for (int j = 0; j < 4; j++) {
                    int nx = cx + dx[j];
                    int ny = cy + dy[j];
                    if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && 
                        !visited[nx][ny] && grid[nx][ny] == 0) {
                        distances[nx][ny] += step;
                        houses[nx][ny]++;
                        qx.offer(nx);
                        qy.offer(ny);
                        visited[nx][ny] = true;
                    }
                }
            }
            step++;
        }
    }
}
//time: O(n^2), space: O(n^2)
//不要创建一个新的Point class, 因为没有写comparator, 不好比较是不是visit过