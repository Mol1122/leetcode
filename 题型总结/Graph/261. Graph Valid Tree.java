public class Solution {
  public boolean validTree(int n, int[][] edges) {
      if (n != edges.length + 1) {
          return false;
      }
      UnionFind uf = new UnionFind(n);
      for (int[] edge : edges) {
          if (uf.find(edge[0]) == uf.find(edge[1])) {
              return false;
          }
          uf.union(edge[0], edge[1]);
      }
      return true;
  }
}

class UnionFind {
    int[] father = null;
    
    public UnionFind(int n) {
        father = new int[n];
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
        }
    }
}

/* 算法：利用union find去判断两个node是否在一个集合，如果在未连接前就在同一个集合，说明是错的
   time: O(E), space: O(V)
*/
