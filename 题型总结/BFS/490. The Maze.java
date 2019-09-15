/* There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, 
but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of 
the maze are all walls. The start and destination coordinates are represented by row and column indexes.

 

Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true

Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right. */

class Solution {
    int[] deltaX = {1, 0, -1, 0};
    int[] deltaY = {0, 1, 0, -1};
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if(maze == null || maze.length == 0 || maze[0].length == 0 || 
           start == null || start.length == 0 || destination == null || 
           destination.length == 0){
            return false;
        }
        
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        
        while(!queue.isEmpty()){
            int[] curPoint = queue.poll();
            int x = curPoint[0], y = curPoint[1];
            for(int j = 0; j < 4; j++){
                int xx = x;
                int yy = y;
                
                while(isValid(xx + deltaX[j], yy + deltaY[j], maze)){
                    xx += deltaX[j];
                    yy += deltaY[j];
                }
                if(xx == destination[0] && yy == destination[1]){
                    return true;
                }
                if(!visited[xx][yy]){
                    queue.offer(new int[]{xx, yy});
                    visited[xx][yy] = true;
                }
            }
        }
        return false;
    }
    
    private boolean isValid(int x, int y, int[][] maze){
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
    }
}