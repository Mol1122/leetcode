/* Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.

You need to support the following method:

connect(a, b), an edge to connect node a and node b
query(a), Returns the number of connected component nodes which include node a.
Example
Example 1:

Input:
ConnectingGraph2(5)
query(1)
connect(1, 2)
query(1)
connect(2, 4)
query(1)
connect(1, 4)
query(1)
Output:[1,2,3,3]
Example 2:

Input:
ConnectingGraph2(6)
query(1)
query(2)
query(1)
query(5)
query(1)

Output:
[1,1,1,1,1] */

public class ConnectingGraph2 {
    UnionFind uf;
    public ConnectingGraph2(int n) {
        uf = new UnionFind(n);
    }

    public void connect(int a, int b) {
        uf.union(a, b);
    }

    public int query(int a) {
        int root_a = uf.find(a);
        return uf.size[root_a];
    }
}

class UnionFind {
    int[] father = null;
    int[] size = null;
    
    public UnionFind(int n) {
        father = new int[n + 1];
        size = new int[n + 1];
        
        for (int i = 1; i < n + 1; i++) {
            father[i] = i;
            size[i] = 1;
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
            size[root_b] += size[root_a];
        }
    }
}