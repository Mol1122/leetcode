/* Description
On a board whose coordinates can go from 
−
∞
−∞ to 
+
∞
+∞, given that the knight is at the (0, 0) coordinate of the board, and now given an endpoint coordinate (x, y), find the shortest route to the endpoint and return the length of the shortest route, with the topic ensuring that the route to the endpoint must exist. 

Path length refers to the number of steps taken by the knight
If the knight's position is (a, b), he can reach these positions next:

(a + 1, b + 2)
(a + 1, b - 2)
(a - 1, b + 2)
(a - 1, b - 2)
(a + 2, b + 1)
(a + 2, b - 1)
(a - 2, b + 1)
(a - 2, b - 1)
−
300
≤
x
,
 
y
≤
300
−300≤x, y≤300
0
≤
∣
x
∣
 
+
 
∣
y
∣
≤
300
0≤∣x∣ + ∣y∣≤300

Example
Example 1:

Input:
x = 2, y = 3
Output:
3
Explanation:
(0, 0) -> (2, 1) -> (0, 2) -> (2, 3)
Example 2:

Input:
x = 1, y = 0
Output:
3
Explanation:
(0, 0) -> (1, 2) -> (3, 1) -> (1, 0)   */


public class Solution {
    /**
     * @param x: The x-coordinate of the endpoint
     * @param y: The y-coordinate of the endpoint
     * @return: The shortest path 
     */
    int n = 700;
    public int shortestPath(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }
        x += 310; //based on the value range
        y += 310;
        Queue<int[]> start = new LinkedList<>();
        Queue<int[]> end = new LinkedList<>();
        Map<Integer, Integer> visited_start = new HashMap<>(); //visited -> step
        Map<Integer, Integer> visited_end = new HashMap<>();
        //Point start_point = new Point(310, 310), end_point = new Point(x, y);
        start.offer(new int[]{310, 310});
        end.offer(new int[]{x, y});
        visited_start.put(310 * n + 310, 0);
        visited_end.put(x * n + y, 0);

        while (!start.isEmpty() && !end.isEmpty()) {
            int result = start.size() <= end.size() ? bfs(visited_start, visited_end, start) : bfs(visited_end, visited_start, end);
            if (result != -1) {
                return result;
            }
        }
        return -1;
    }

    private int bfs(Map<Integer, Integer> visited_start, Map<Integer, Integer> visited_end, Queue<int[]> queue) {
        int[] dx = {1, 1, -1, -1, 2, 2, -2, -2};
        int[] dy = {2, -2, 2, -2, 1, -1, 1, -1};

        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int[] p = queue.poll();
            int step = visited_start.get(p[0] * n + p[1]);

            for (int j = 0; j < 8; j++) {
                int nx = p[0] + dx[j];
                int ny = p[1] + dy[j];
                //int[] next = new int[]{nx * n + ny};
                if (visited_start.containsKey(nx * n + ny)) {
                    continue;
                }
                if (visited_end.containsKey(nx * n + ny)) {
                    return step + visited_end.get(nx * n + ny) + 1;
                }
                queue.offer(new int[]{nx, ny});
                visited_start.put(nx * n + ny, step + 1);
            }
        }
        return -1; //走完这一个level都没有跟相遇
    }
}

class Point { //这题不能用Point, 因为会超memory
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
//算法：双向BFS