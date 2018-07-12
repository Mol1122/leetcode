class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (flights == null || flights.length == 0) {
           return Integer.MAX_VALUE; 
        }
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] flight : flights) {
            if (!map.containsKey(flight[0])) {
                map.put(flight[0], new HashMap<>());
            }
            map.get(flight[0]).put(flight[1], flight[2]);
        }
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0]- b[0]);
        pq.offer(new int[]{0, src, K + 1});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int price = top[0];
            int city = top[1];
            int stop = top[2];
            if (city == dst) {
                return price;
            }
            if (stop > 0) {
                Map<Integer, Integer> adj = map.getOrDefault(city, new HashMap<>());
                for (int a : adj.keySet()) {
                    pq.offer(new int[]{price + adj.get(a), a, stop - 1});
                }
            }
        }
        return -1;
    }
}

/* 算法：动态规划。 Dijkstra's algorithm
** 时间复杂度：O(|E|+|V|*log|V|) E:# of edges, V:# of nodes*/