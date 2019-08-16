/* There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture. */

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (flights == null || flights.length == 0) {
            return -1;
        }
        Map<Integer, Map<Integer, Integer>> city2neighbors = new HashMap<>();
        for (int[] flight : flights) {
            city2neighbors.putIfAbsent(flight[0], new HashMap<>());
            city2neighbors.get(flight[0]).put(flight[1], flight[2]);
        }
        
        Queue<Pair> minheap = new PriorityQueue<>(new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.cost - b.cost;
            }
        });
        minheap.offer(new Pair(0, src, K));
        
        while (!minheap.isEmpty()) {
            Pair p = minheap.poll();
            if (p.city == dst) {
                return p.cost;
            }
            if (p.stop >= 0) {
                Map<Integer, Integer> neighbor2cost = city2neighbors.getOrDefault(p.city, new HashMap<>());
                for (int neighbor : neighbor2cost.keySet()) {
                    minheap.offer(new Pair(p.cost + neighbor2cost.get(neighbor), neighbor, p.stop - 1));
                }
            }
        }
        return -1;
    }
}

class Pair {
    int cost, city, stop;
    
    public Pair(int cost, int city, int stop) {
        this.cost = cost;
        this.city = city;
        this.stop = stop;
    }
}
//time: O(E + VlogV), space: O(V)