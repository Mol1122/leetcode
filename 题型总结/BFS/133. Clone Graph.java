/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

//Version 1
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

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

//Version 2
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        List<Node> nodes = getNodes(node);
        Map<Node, Node> map = new HashMap<>();
        
        for (Node curr : nodes) {
            map.put(curr, new Node(curr.val));
        }
        
        for (Node curr : map.keySet()) {
            for (Node neighbor : curr.neighbors) {
                map.get(curr).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }
    
    private List<Node> getNodes(Node node) {
        List<Node> results = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        
        queue.offer(node);
        visited.add(node);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            results.add(curr);
            for (Node neighbor : curr.neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return results;
    }
}