public class Solution {
  public boolean canFinish(int n, int[][] pre) {
      if (pre == null || pre.length == 0) {
          return true;
      }
      Map<Integer, List<Integer>> graph = getGraph(n, pre);
      Map<Integer, Integer> indegree = getIndegree(graph);
    
      Queue<Integer> queue = new LinkedList<>();
      for (Integer node : indegree.keySet()) {
          if (indegree.get(node) == 0) {
            queue.offer(node);
          }
      }
      List<Integer> results = new ArrayList<>();
      while (!queue.isEmpty()) {
          int node = queue.poll();
          results.add(node);
          for (Integer neighbor : graph.get(node)) {
              indegree.put(neighbor, indegree.get(neighbor) -1);
              if (indegree.get(neighbor) == 0) {
                  queue.offer(neighbor);
              }
          }
      }
      return results.size() == n;
  }  
  
  private Map<Integer, Integer> getIndegree(Map<Integer, List<Integer>> graph) {
      Map<Integer, Integer> indegree = new HashMap<>();
      for (Integer node : graph.keySet()) {
          indegree.put(node, 0);
      }
      for (Integer node : graph.keySet()) {
          for (Integer neighbor : graph.get(node)) {
              indegree.put(neighbor, indegree.get(neighbor) + 1);
          }
      }
      return indegree;
  }
  
  private Map<Integer, List<Integer>> getGraph(int n, int[][] pre) {
      Map<Integer, List<Integer>> graph = new HashMap<>();
      for (int i = 0; i < n; i++) {
          graph.put(i, new ArrayList<>());
      }
      for (int[] item : pre) {
          graph.get(item[1]).add(item[0]);
      }
      return graph;
  }
}
/* 算法：拓扑排序 
** time: O(V + E), space: O(V + E)
*/
