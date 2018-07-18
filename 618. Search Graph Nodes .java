/**
 * Definition for graph node.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { 
 *         label = x; neighbors = new ArrayList<UndirectedGraphNode>(); 
 *     }
 * };
 */


public class Solution {
    /*
     * @param graph: a list of Undirected graph node
     * @param values: a hash mapping, <UndirectedGraphNode, (int)value>
     * @param node: an Undirected graph node
     * @param target: An integer
     * @return: a node
     */
    public UndirectedGraphNode searchNode(ArrayList<UndirectedGraphNode> graph,
                                          Map<UndirectedGraphNode, Integer> values,
                                          UndirectedGraphNode node,
                                          int target) {
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> set = new HashSet();
        
        queue.offer(node);
        set.add(node);
        
        while (!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.poll();
            set.add(curr);
            if (values.get(curr) == target) {
                return curr;
            }
            for (UndirectedGraphNode v : curr.neighbors) {
                if (!set.contains(v)) {
                    queue.offer(v);
                }
            }
        }
        return null;
    }
}

/* 算法：BFS找最短路径的问题, 因为不return step所以不需要queue.size() */