/*
* class GraphNode {
*   public int key;
*   public List<GraphNode> neighbors;
*   public GraphNode(int key) {
*     this.key = key;
*     this.neighbors = new ArrayList<GraphNode>();
*   }
* }
*/
public class Solution {
  public List<GraphNode> copy(List<GraphNode> graph) {
      //iterative
   /*   List<GraphNode> results = new ArrayList<>();
      if (graph == null || graph.size() == 0) {
          return results;
      }
      Map<GraphNode, GraphNode> map = new HashMap<>();
      for (GraphNode node : graph) {
          if (!map.containsKey(node)) {
              GraphNode newNode = new GraphNode(node.key);
              map.put(node, newNode);
              results.add(newNode);
          }
      }
      for (GraphNode node : graph) {
          for (GraphNode  neighbor: node.neighbors) {
              GraphNode newNeighbor = map.get(neighbor);
              map.get(node).neighbors.add(newNeighbor);
          }
      }
      return results;  */
    
      //Recursion
      List<GraphNode> results = new ArrayList<>();
      if (graph == null || graph.size() == 0) {
          return results;
      }
      Map<GraphNode, GraphNode> map = new HashMap<>();
      for (GraphNode node : graph) {
          if (!map.containsKey(node)) {
              map.put(node, new GraphNode(node.key));
              dfs(node, map);
          }
          results.add(map.get(node));
      }
      return results;
  }
  
  private void dfs(GraphNode node, Map<GraphNode, GraphNode> map) {
      for (GraphNode neighbor : node.neighbors) {
          if (!map.containsKey(neighbor)) {
              map.put(neighbor, new GraphNode(neighbor.key));
              dfs(neighbor, map);
          }
          map.get(node).neighbors.add(map.get(neighbor));
      }
  }
}
//time: O(V + E), space: O(V)