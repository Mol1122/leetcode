/* There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.

This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It's guaranteed that each city can reach city 0 after reorder.

 

Example 1:


Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 2:


Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
Output: 2
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 3:

Input: n = 3, connections = [[1,0],[2,0]]
Output: 0 */


class Solution {
    public int minReorder(int n, int[][] connections) {
        if (connections == null || connections.length == 0) {
            return 0;
        }
        Map<Integer, List<Integer>> graph = getGraph(n, connections);

        int[] count = {0};
        dfs(graph, new boolean[n], 0, count);

        return count[0];
    }

    private void dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int curr, int[] count) {
        visited[curr] = true;

        for (int neighbor : graph.get(curr)) {
            if (!visited[Math.abs(neighbor)]) {
                if (neighbor > 0) {
                    count[0]++;
                }
                dfs(graph, visited, Math.abs(neighbor), count);
            }
        }
    }

    private Map<Integer, List<Integer>> getGraph(int n, int[][] connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] con : connections) {
            graph.get(con[0]).add(con[1]);
            graph.get(con[1]).add(-con[0]);
        }
        return graph;
    }
}
//time: O(n), space: O(n)