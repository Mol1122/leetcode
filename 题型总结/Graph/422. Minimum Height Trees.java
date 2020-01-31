/* For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then 
a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). 
Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected 
edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as 
[1, 0] and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Note:

(1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”

(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf. */

public class Solution {
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    if (edges == null || edges.length == 0 || n == 0) {
      return new ArrayList<>();
    }
    if (n == 1) {
      return Arrays.asList(0);
    }
    Map<Integer, Set<Integer>> graph = getGraph(edges);
    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> set = new HashSet<>();

    for (int node : graph.keySet()) {
      if (graph.get(node).size() == 1) {
        queue.offer(node);
        set.add(node);
      }
    }

    while (n > 2) {
      int size = queue.size();
      n -= size;

      for (int i = 0; i < size; i++) {
        int node = queue.poll();
        for (int neighbor : graph.keySet()) {
          graph.get(neighbor).remove(node);
          if (graph.get(neighbor).size() == 1) {
            if (!set.contains(neighbor)) {
              queue.offer(neighbor);
              set.add(neighbor);
            }
          }
        }
      }
    }
    return new ArrayList<>(queue);
  }

  private Map<Integer, Set<Integer>> getGraph(int[][] edges) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();

    for (int[] edge : edges) {
      graph.putIfAbsent(edge[0], new HashSet<>());
      graph.get(edge[0]).add(edge[1]);

      graph.putIfAbsent(edge[1], new HashSet<>());
      graph.get(edge[1]).add(edge[0]);
    }
    return graph;
  }
}
/* 算法：拓扑排序
** 难点：要分析得出，最后的答案要么只有一个root, 要么可以有两个root.所以while (n > 2)*/