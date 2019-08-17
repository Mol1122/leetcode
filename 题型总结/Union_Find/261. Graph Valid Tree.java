/* Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges. */

class Solution {
    public boolean validTree(int n, int[][] edges) {
        //判断是否联通
        if (n != edges.length + 1) {
            return false;
        }
        UnionFind uf = new UnionFind(n);
        
        for (int i = 0; i < edges.length; i++) {
            if (uf.find(edges[i][0]) == uf.find(edges[i][1])) {
                return false;
            }
            uf.union(edges[i][0], edges[i][1]);
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

/* 算法： Tree 1. 是连通图 = n node and (n - 1) edges     2. 无环 = 利用unionfind去找两个端点是否属于同一集合，属于说明已经有一条边去连接这两个点，否则，把这两个点连起来 

** 时间复杂度：O(V + E) */