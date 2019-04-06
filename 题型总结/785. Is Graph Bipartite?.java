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