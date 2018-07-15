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
        
        int n = grid.length;
        int m = grid[0].length;
        int[] dx = {1, 1, 2, 2, -1, -1, -2, -2};
        int[] dy = {2, -2, 1, -1, 2, -2, 1, -1};
        
        Queue<Point> queue = new LinkedList<>();
        queue.offer(source);
        
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point point = queue.poll();
                if (point.x == destination.x && point.y == destination.y) {
                    return steps;
                }
                
                for (int direction = 0; direction < 8; direction++) {
                    Point nextPoint = new Point(
                        point.x + dx[direction],
                        point.y + dy[direction]
                    );
                    
                    if (nextPoint.x >= 0 && nextPoint.x < n &&
                        nextPoint.y >= 0 && nextPoint.y < m &&
                        !grid[nextPoint.x][nextPoint.y]) {
                        queue.offer(nextPoint);
                        grid[nextPoint.x][nextPoint.y] = true;
                    }
                }
            }
            steps++;
        }
        
        return -1;
    }
}

/* 算法：需要分层的BFS
** 时间复杂度：O(nm) */