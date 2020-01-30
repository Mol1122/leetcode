/* Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) 
connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid 
are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and 
only if one island can be translated (and not rotated or reflected) to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.
Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Notice that:
11
1
and
 1
11
are considered different island shapes, because we do not consider reflection / rotation.
Note: The length of each dimension in the given grid does not exceed 50. */

class Solution {
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        Set<List<List<Integer>>> results = new HashSet<>();
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    List<List<Integer>> list = bfs(grid, i, j);
                    results.add(list);
                }
            }
        }
        return results.size();
    }
    
    private List<List<Integer>> bfs(int[][] grid, int x, int y) {
        List<List<Integer>> results = new ArrayList<>();
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        qx.offer(x);
        qy.offer(y);
        grid[x][y] = 0;
        
        while (!qx.isEmpty()) {
            int cx = qx.poll();
            int cy = qy.poll();
            List<Integer> list = Arrays.asList(cx - x, cy - y);
            results.add(list);
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && 
                    grid[nx][ny] == 1) {
                    qx.offer(nx);
                    qy.offer(ny);
                    grid[nx][ny] = 0;
                }
            }
        }
        return results;
    }
}
/* 我们可以通过BFS/DFS得到每一个岛屿, 然后把每一个岛屿的形状放到 set 里, 最后 set 的大小就是答案.

那么问题的关键在于如何描述一个岛屿的形状.

有以下两个基本思路:

记录一个岛屿所有点相对于左上角的点的相对位置.
记录一个岛屿的bfs/dfs轨迹
方法1涉及细节较少, 但是可能复杂度相对较高, 不过 50x50 的数据范围不会超时.

方法1也有多种实现方法, 比如一个岛屿形状可以用set记录, 也可以将所有点的相对坐标排序后转换成字符串.

方法2需要注意一个细节: 不能仅仅储存下来dfs/bfs移动的方向, 因为涉及到回溯等问题, 可以加上一定的间隔符, 或者除方向之外额外记录一个位置信息. */