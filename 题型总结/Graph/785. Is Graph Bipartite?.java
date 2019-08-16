/* Determine if an undirected graph is bipartite. A bipartite graph is one in which the nodes can be divided into two groups such that no nodes have direct edges to other nodes in the same group.

Examples

1  --   2

    /   

3  --   4

is bipartite (1, 3 in group 1 and 2, 4 in group 2).

1  --   2

    /   |

3  --   4

is not bipartite.

Assumptions

The graph is represented by a list of nodes, and the list of nodes is not null. */

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



public class Solution {
  public boolean isBipartite(List<GraphNode> graph) {
    if (graph == null || graph.size() == 0) {
        return true;
    }
    Map<GraphNode, Integer> node2group = new HashMap<>();
    for (GraphNode node : graph) {
        if (!sameGroup(node, node2group)) {
            return false;
        }
    }
    return true;
  }

  private boolean sameGroup(GraphNode node, Map<GraphNode, Integer> node2group) {
    if (node2group.containsKey(node)) {
        return true;
    }
    node2group.put(node, 0);
    Queue<GraphNode> queue = new LinkedList<>();
    queue.offer(node);

    while (!queue.isEmpty()) {
        GraphNode curr = queue.poll();
        int nextGroup = node2group.get(curr) == 0 ? 1 : 0;

        for (GraphNode neighbor : curr.neighbors) {
            if (!node2group.containsKey(neighbor)) {
                node2group.put(neighbor, nextGroup);
                queue.offer(neighbor);
            } else {
                if (node2group.get(neighbor) != nextGroup) {
                    return false;
                }
            }
        }
    }
    return true;
  }
}


/* 算法：bfs，curr的neighbors一定在另外一个group
** 时间复杂度：O(V+E)
** 空间复杂度：O(V) */