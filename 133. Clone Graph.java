/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        //get all the nodes in the old graph
        List<UndirectedGraphNode> nodes = getNodes(node);
        //copy all the nodes, create a map old -> new
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        for (UndirectedGraphNode n : nodes) {
            map.put(n, new UndirectedGraphNode(n.label));
        }
        //copy all the edges = copy all the neighbors of all the nodes
        for (UndirectedGraphNode n : nodes) {
            UndirectedGraphNode newNode = map.get(n);
            for (UndirectedGraphNode neighbor : n.neighbors) {
                UndirectedGraphNode nb = map.get(neighbor);
                newNode.neighbors.add(nb);
            }
        }
        return map.get(node);
    }
    
    private List<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        
        while (!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.poll();
            for (UndirectedGraphNode neighbor : curr.neighbors) {
                if (!set.contains(neighbor)) {
                    queue.offer(neighbor);
                    set.add(neighbor);
                }
            }
        }
        return new ArrayList<UndirectedGraphNode>(set);
    }
}

/* 算法：1. 一边找到所有的点，一边复制所有的点
**      2. 一边复制所有的边
** 时间复杂度： O(n^2) */