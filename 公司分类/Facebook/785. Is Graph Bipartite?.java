/* Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B 
such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between 
nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self 
edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.
  */

class Solution {
    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return false;
        }
        Map<Integer, Integer> node2group = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            if (!isSameGroup(graph, i, node2group)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isSameGroup(int[][] graph, int node, Map<Integer, Integer> node2group) {
        if (node2group.containsKey(node)) {
            return true;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);
        
        node2group.put(node, 0);
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int currGroup = node2group.get(curr);
            int nextGroup = currGroup == 0 ? 1 : 0;
            
            for (int neighbor : graph[curr]) {
                if (!node2group.containsKey(neighbor)) {
                    node2group.put(neighbor, nextGroup);
                    queue.offer(neighbor);
                } else if (node2group.get(neighbor) != nextGroup) {
                    return false;
                }
            }
        }
        return true;
    }
}

/* 算法：bfs，curr的neighbors一定在另外一个group
** 时间复杂度：O(V+E)
** 空间复杂度：O(V) */