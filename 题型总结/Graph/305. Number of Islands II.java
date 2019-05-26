/* A 2d grid map of m rows and n columns is initially filled with water. 
We may perform an addLand operation which turns the water at position (row, col) into a land. 
Given a list of positions to operate, count the number of islands after each addLand operation. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3] */

public class Solution {
  public List<Integer> numIslands(int m, int n, int[][] positions) {
      List<Integer> results = new ArrayList<>();
      if (positions == null || positions.length == 0) {
          return results;
      }
      UnionFind uf = new UnionFind(m * n);
      int[][] matrix = new int[m][n];
      int[] dx = {1, 0, -1, 0};
      int[] dy = {0, 1, 0, -1};
    
      for (int[] pos : positions) {
          int x = pos[0];
          int y = pos[1];
          if (matrix[x][y] == 0) {
              matrix[x][y] = 1;
              uf.add();
            
              for (int i = 0; i < 4; i++) {
                  int nx = x + dx[i];
                  int ny = y + dy[i];
                  if (nx >= 0 && nx < m && ny >= 0 && ny < n && matrix[nx][ny] == 1) {
                      uf.union(getId(x, y, n), getId(nx, ny, n));
                  }
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
    int size = 0;
  
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
  
    public void add() {
        size++;
    }
  
    public int query() {
        return size;
    }
}

/* 算法：利用union find, 每次加一个position, size都先++，然后遍历周围4个点，如果相邻就union 
time: O(pos.length), space: O(n * m)

*/