/* You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.

Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.

 

Example 1:

Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Example 2:

Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1
 */

 class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (routes == null || routes.length == 0) {
            return -1;
        }
        if (source == target) {
            return 0;
        }
        Map<Integer, Set<Integer>> map = getMap(routes);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[map.size()];

        for (int key : map.keySet()) {
            if (map.get(key).contains(source)) {
                queue.offer(key);
                visited[key] = true;
            }
        }
        int step = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (map.get(curr).contains(target)) {
                    return step;
                }
                for (int next : map.get(curr)) {
                    for (int bus : map.keySet()) {
                        if (map.get(bus).contains(next) && !visited[bus]) {
                            queue.offer(bus);
                            visited[bus] = true;
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private Map<Integer, Set<Integer>> getMap(int[][] routes) {
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int i = 0; i < routes.length; i++) {
            map.put(i, new HashSet<>());
            for (int item : routes[i]) {
                map.get(i).add(item);
            }
        }
        return map;
    }

//time: O(n^2 * k), k is the size of each route, space: O(n * k)