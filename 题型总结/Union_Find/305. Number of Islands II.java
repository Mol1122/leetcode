/* A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions? */

class Solution {
    public List<Integer> numIslands2(int n, int m, int[][] positions) {
        List<Integer> results = new ArrayList<>();
        if (m <= 0 || n <= 0 || positions == null || positions.length == 0) {
            return results;
        }
        UnionFind uf = new UnionFind(n * m);
        int[][] matrix = new int[n][m];
        
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        for (int[] position : positions) {
            int x = position[0];
            int y = position[1];
            
            if (matrix[x][y] == 0) {
                uf.addCount();
                matrix[x][y] = 1;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && matrix[nx][ny] == 1) {
                    uf.union(getId(nx, ny, m), getId(x, y, m));
                }
            }
            results.add(uf.query());
        }
        return results;
    }
    
    private int getId(int x, int y, int col) {
        return x * col + y;
    }
}

class UnionFind {
    int[] father = null;
    int size;
    
    public UnionFind(int n) {
        father = new int[n];
        size = 0;
        
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }
    
    public int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }
    
    public void union(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);
        
        if (root_a != root_b) {
            father[root_a] = root_b;
            size--;
        }
    }
    
    public void addCount() {
        size++;
    }
    
    public int query() {
        return size;
    }
}