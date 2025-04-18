/* 
You are given an empty 2D binary grid grid of size m x n. The grid represents a map where 0's represent water and 1's represent land. Initially, all the cells of grid are water cells (i.e., all the cells are 0's).

We may perform an add land operation which turns the water at position into a land. You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.

Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
Example 1:

Input: n = 4, m = 5, A = [[1,1],[0,1],[3,3],[3,4]]
Output: [1,1,2,2]
Explanation:
0.  00000
    00000
    00000
    00000
1.  00000
    01000
    00000
    00000
2.  01000
    01000
    00000
    00000
3.  01000
    01000
    00000
    00010
4.  01000
    01000
    00000
    00011
Example 2:

Input: n = 3, m = 3, A = [[0,0],[0,1],[2,2],[2,1]]
Output: [1,1,2,2]                                                   */

 class Solution {
    public List<Integer> numIslands2(int n, int m, int[][] positions) {
        List<Integer> results = new ArrayList<>();
        if (positions == null || positions.length == 0) {
            return results;
        }
        UnionFind uf = new UnionFind(n * m);
        boolean[][] matrix = new boolean[n][m];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        for (int[] pos : positions) { //input可能会有相同的pos
            int cx = pos[0];
            int cy = pos[1];
            if (!matrix[cx][cy]) {
                matrix[cx][cy] = true;
                uf.size++;
                
                for (int i = 0; i < 4; i++) { //不需要用queue 一层一层地向外延伸，只要check周围四个邻居就知道是否相连了
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && matrix[nx][ny]) {
                        uf.union(cx * m + cy, nx * m + ny);
                    }
                }
            }
            results.add(uf.size);
        }
        return results;
    }
}

class UnionFind {
    int[] father;
    int size;
    
    public UnionFind(int n) {
        father = new int[n];
        size = 0;
        
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }
    
    public void union(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);
        
        if (root_a != root_b) {
            father[root_a] = root_b;
            size--;
        }
    }
    
    public int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }
}