/* You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.

All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.

For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.

 

Example 1:


Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
Output: ["JFK","MUC","LHR","SFO","SJC"]
Example 2:


Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 */

 class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> results = new ArrayList<>();
        if (tickets == null || tickets.size() == 0) {
            return results;
        }
        Map<String, Queue<String>> graph = new HashMap<>();

        for (List<String> ticket : tickets) {
            String depart = ticket.get(0);
            String dest = ticket.get(1);

            graph.putIfAbsent(depart, new PriorityQueue<>());
            graph.get(depart).add(dest);
        }

        dfs(graph, "JFK", results);
        Collections.reverse(results);
        return results;
    }

    private void dfs(Map<String, Queue<String>> graph, String curr, List<String> results) {
        while (graph.containsKey(curr) && !graph.get(curr).isEmpty()) {
            String neighbor = graph.get(curr).poll();
            dfs(graph, neighbor, results);
        }
        results.add(curr);
    }
}
//time: O(ElogE), space: O(V+E)
//这题是欧拉路径（Eulerian Path）问题，要等递归走到底，再在回溯阶段把节点加入结果