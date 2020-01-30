/* Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], 
reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, 
the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical 
order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order. */

class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> results = new ArrayList<>();
        if (tickets == null || tickets.size() == 0) {
            return results;
        }
        Map<String, Queue<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            map.putIfAbsent(ticket.get(0), new PriorityQueue<String>());
            map.get(ticket.get(0)).offer(ticket.get(1));
        }
        dfs("JFK", map, results);
        Collections.reverse(results);
        return results;
    }
    
    private void dfs(String curr, Map<String, Queue<String>> map, List<String> results) {
        while (map.containsKey(curr) && !map.get(curr).isEmpty()) {
            dfs(map.get(curr).poll(), map, results);
        }
        results.add(curr); //保证把lexical order最大的先加进去
    }
}
//难点：因为这不是一个先进先出的关系，所以不能用BFS
/* 这题的本质是求在一个有向图中求一条经过所有边的路径
** 算法：欧拉路径
** 时间复杂度：O(E) */