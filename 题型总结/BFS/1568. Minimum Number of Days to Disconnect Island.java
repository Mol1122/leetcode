/* You are given an m x n binary grid grid where 1 represents land and 0 represents water. An island is a maximal 4-directionally (horizontal or vertical) connected group of 1's.

The grid is said to be connected if we have exactly one island, otherwise is said disconnected.

In one day, we are allowed to change any single land cell (1) into a water cell (0).

Return the minimum number of days to disconnect the grid.

 

Example 1:


Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]

Output: 2
Explanation: We need at least 2 days to get a disconnected grid.
Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.
Example 2:


Input: grid = [[1,1]]
Output: 2
Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands. */

class Solution {
    public int minDays(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        if (isDisconnected(grid)) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    if (isDisconnected(grid)) {
                        return 1;
                    }
                    grid[i][j] = 1;
                }
            }
        }
        return 2;
    }

    private boolean isDisconnected(int[][] grid) {
        int count = 0;
        int[][] newGrid = new int[grid.length][grid[0].length];

        //depp copy grid to newGrid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                newGrid[i][j] = grid[i][j];
            }
        }

        for (int i = 0; i < newGrid.length; i++) {
            for (int j = 0; j < newGrid[0].length; j++) {
                if (newGrid[i][j] == 1) {
                    count++;
                    newGrid[i][j] = 0;
                    bfs(newGrid, i, j);
                }
            }
        }
        return count != 1;
    }

    private void bfs(int[][] grid, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == 1) {
                    queue.offer(new int[]{nx, ny});
                    grid[nx][ny] = 0;
                }
            }
        }
    }
}

//算法： 这是一个只有一层的dfs。因为只需要iteratre所有cell, 然后判断只变一个cell能不能disconnected, 如果不能直接return 2, 不会有接下来变第二和第三个的步骤
//trick： a fully connected grid of land cells always needs two changes to disconnect.