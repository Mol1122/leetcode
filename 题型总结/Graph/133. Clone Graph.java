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
      List<GraphNode> results = new ArrayList<>();
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
      return results;
  }
}
//time: O(V + E), space: O(V)