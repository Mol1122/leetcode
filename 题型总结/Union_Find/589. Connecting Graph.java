/* Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.

You need to support the following method:

connect(a, b), add an edge to connect node a and node b`.
query(a, b), check if two nodes are connected
Example
Example 1:

Input:
ConnectingGraph(5)
query(1, 2)
connect(1, 2)
query(1, 3) 
connect(2, 4)
query(1, 4) 
Output:
[false,false,true]

Example 2:

Input:
ConnectingGraph(6)
query(1, 2)
query(2, 3)
query(1, 3)
query(5, 6)
query(1, 4)

Output:
[false,false,false,false,false] */

public class ConnectingGraph {
    UnionFind uf;
    public ConnectingGraph(int n) {
        uf = new UnionFind(n);
    }

    public void connect(int a, int b) {
        uf.union(a, b);
    }
    
    public boolean query(int a, int b) {
        return uf.find(a) == uf.find(b);
    }
}

class UnionFind {
    int[] father = null;
    
    public UnionFind(int n) {
        father = new int[n + 1];
        for (int i = 1; i <= n; i++) {
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
//time: O(V), space: O(V)