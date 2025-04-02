/* You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.

Return the number of connected components in the graph.

 

Example 1:


Input: n = 5, edges = [[0,1],[1,2],[3,4]]
Output: 2
Example 2:


Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
Output: 1
                                                  */

//Method 1
class Solution {
    public int countComponents(int n, int[][] edges) {
        if (edges == null || edges.length == 0) {
            return n;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (uf.find(edge[0]) != uf.find(edge[1])) {
                uf.union(edge[0], edge[1]);
            }
        }
        return uf.count;
    }
}

class UnionFind {
    int[] father = null;
    int count = 0;
    
    public UnionFind(int n) {
        father = new int[n];
        count = n;
        
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
            count--;
        }
    }
}


//Method 2
class Solution {
    public int countComponents(int n, int[][] edges) {
        if (edges == null || edges.length == 0) {
            return n;
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]); //undirected需要双方成彼此的neighbor
        }
        
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                bfs(i, graph, visited);
                count++;
            } 
        }
        return count;
    }

    private void bfs(int root, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        visited.add(root);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }
}