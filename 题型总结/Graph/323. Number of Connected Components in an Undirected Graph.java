public class Solution {
  public int countComponents(int n, int[][] edges) {
      UnionFind uf = new UnionFind(n);
      for (int[] edge : edges) {
          uf.union(edge[0], edge[1]);
      }
      return uf.size;
  }
}

class UnionFind {
    int[] father = null;
    int size;
  
    public UnionFind(int n) {
        father = new int[n];
        size = n;
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
}
//time: O(n), space: O(n)