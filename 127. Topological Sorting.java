/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */

public class Solution {
    /*
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> results = new ArrayList<>();
        Map<DirectedGraphNode, Integer> map = new HashMap<>();
        //calculate the indegree
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, 1);
                } else {
                    map.put(neighbor, map.get(neighbor) + 1);
                }
            }
        }
        
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node)) {
                results.add(node);
                queue.offer(node);
        
            }
        }
        
        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            for (DirectedGraphNode neighbor : node.neighbors) {
                map.put(neighbor, map.get(neighbor) - 1);
                if (map.get(neighbor) == 0) {
                    queue.offer(neighbor);
                    results.add(neighbor);
                }
            }
        }
        return results;
    }
}

/* 算法描述：
1. 统计每个点的入度
2. 将每个入度为 0 的点放入队列（Queue）中作为起始节点
3. 不断从队列中拿出一个点，去掉这个点的所有连边（指向其他点的边），其他点的相应的入度 - 1
4. 一旦发现新的入度为 0 的点，丢回队列中
*/