/* There is an undirected graph with n nodes numbered from 0 to n - 1 (inclusive). You are given a 0-indexed integer array values where values[i] is the value of the ith node. You are also given a 0-indexed 2D integer array edges, where each edges[j] = [uj, vj, timej] indicates that there is an undirected edge between the nodes uj and vj, and it takes timej seconds to travel between the two nodes. Finally, you are given an integer maxTime.

A valid path in the graph is any path that starts at node 0, ends at node 0, and takes at most maxTime seconds to complete. You may visit the same node multiple times. The quality of a valid path is the sum of the values of the unique nodes visited in the path (each node's value is added at most once to the sum).

Return the maximum quality of a valid path.

Note: There are at most four edges connected to each node.

 

Example 1:


Input: values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime = 49
Output: 75
Explanation:
One possible path is 0 -> 1 -> 0 -> 3 -> 0. The total time taken is 10 + 10 + 10 + 10 = 40 <= 49.
The nodes visited are 0, 1, and 3, giving a maximal path quality of 0 + 32 + 43 = 75.
Example 2:


Input: values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime = 30
Output: 25
Explanation:
One possible path is 0 -> 3 -> 0. The total time taken is 10 + 10 = 20 <= 30.
The nodes visited are 0 and 3, giving a maximal path quality of 5 + 20 = 25.
Example 3:


Input: values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]], maxTime = 50
Output: 7
Explanation:
One possible path is 0 -> 1 -> 3 -> 1 -> 0. The total time taken is 10 + 13 + 13 + 10 = 46 <= 50.
The nodes visited are 0, 1, and 3, giving a maximal path quality of 1 + 2 + 4 = 7. */

class Solution {
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        if (values == null || values.length == 0) {
            return 0;
        }
        Map<Integer, List<int[]>> graph = new HashMap<>(); //key: node, value: [neighbor, time between node and neighbor]

        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
        int n = values.length;
        boolean[] visited = new boolean[n];
        int[] maxValue = {Integer.MIN_VALUE};

        dfs(graph, 0, values, visited, 0, maxTime, 0, maxValue);
        return maxValue[0];
    }

    private void dfs(Map<Integer, List<int[]>> graph, int curr, int[] values, boolean[] visited, int currTime, int maxTime, int currValue, int[] maxValue) {
        if (currTime > maxTime) {
            return;
        }
        boolean isFirstVisit = !visited[curr];
        visited[curr] = true;
        if (isFirstVisit) {
            currValue += values[curr];
        }
        if (curr == 0) {
            maxValue[0] = Math.max(maxValue[0], currValue);
        }

        for (int[] neighbor : graph.getOrDefault(curr, new ArrayList<>())) {
            dfs(graph, neighbor[0], values, visited, currTime + neighbor[1], maxTime, currValue, maxValue);
        }
        if (isFirstVisit) {
            visited[curr] = false;
        }
    }
}

//time: O(E*2^N), space: O(N+E)