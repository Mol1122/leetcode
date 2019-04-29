class Solution {
    public List<String> findItinerary(String[][] tickets) {
        List<String> results = new ArrayList<>();
        if (tickets == null || tickets.length == 0) {
            return results;
        }
        Map<String, Queue<String>> map = new HashMap<>();
        for (String[] ticket : tickets) {
            map.putIfAbsent(ticket[0], new PriorityQueue<String>());
            map.get(ticket[0]).add(ticket[1]);
        }
        dfs("JFK", map, results);
        Collections.reverse(results);
        return results;
    }
    
    private void dfs(String curr, Map<String, Queue<String>> map, 
                     List<String> results) {
        while (map.containsKey(curr) && !map.get(curr).isEmpty()) {
            dfs(map.get(curr).poll(), map, results);
        }
        results.add(curr);
    }
}

/* 这题的本质是求在一个有向图中求一条经过所有边的路径
** 算法：欧拉路径
** 时间复杂度：O(E) */