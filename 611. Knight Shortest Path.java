/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class Solution {
    /**
     * @param grid: a chessboard included 0 (false) and 1 (true)
     * @param source: a point
     * @param destination: a point
     * @return: the shortest path 
     */
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int[] dx = {1, 1, 2, 2, -1, -1, -2, -2};
        int[] dy = {2, -2, 1, -1, 2, -2, 1, -1};
        int n = grid.length;
        int m = grid[0].length;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(source);

        int steps = 0;
        
        while (!queue.isEmpty()) {
            //System.out.println("size = " + queue.size());
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point c = queue.poll();
                //System.out.println("x = " + c.x + " y = " + c.y);
                if (c.x == destination.x && c.y == destination.y) {
                    return steps;
                }
                
                for (int j = 0; j < 8; j++) {
                    Point np = new Point(c.x + dx[j], c.y + dy[j]);
                    if (np.x >= 0 && np.x < n && np.y >= 0 && np.y < m && grid[np.x][np.y] == false) {
                        queue.offer(np);
                        grid[np.x][np.y] = true; //走过的点不能再走
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}
/* 算法：分层的BFS，从queue拿出来一个，判断一下是不是destination，这样step就是在最后增加. 如果是向之前就step++,那么在放进queue的时候就要判断是不是destination, 这样代码有些冗余
** 难点：需要分层*/