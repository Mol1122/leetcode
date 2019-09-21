/* On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.

Here, the north-west corner of the grid is at the first row and column, and the south-east 
corner of the grid is at the last row and column.

Now, we walk in a clockwise spiral shape to visit every position in this grid. 

Whenever we would move outside the boundary of the grid, we continue our walk outside the grid 
(but may return to the grid boundary later.) 

Eventually, we reach all R * C spaces of the grid.

Return a list of coordinates representing the positions of the grid in the order they were visited.

 

Example 1:

Input: R = 1, C = 4, r0 = 0, c0 = 0
Output: [[0,0],[0,1],[0,2],[0,3]] */

class Solution {
    /*  Time Complexity: the worse condition is when the starting point
        is in the south-east corner of the grid, which requires the function
        to traveal through (2max(R,C)) ^ 2 points thus the TM would be 
        O((max(R, C)^2)). Space complexity would be O(R*C)*/
    
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        // direction: right, down, left, up
        int[] dirRow = new int[]{0, 1, 0, -1};
        int[] dirCol = new int[]{1, 0, -1, 0};
        
        int[][] result = new int[R*C][2];
        result[0] = new int[]{r0, c0};
        // use count to keep track of number of nodes that have been found
        // use k to keep track of direction and steps
        int count = 1, k = 1;
        
        while (count != R*C) {
            // get direction
            int dir = (k - 1) % 4;
            // get number of steps
            int step = k % 2 == 0 ? k / 2 : (k + 1) / 2;
            
            // move in this direction
            for (int i = 0; i < step; i++) {
                r0 += dirRow[dir];
                c0 += dirCol[dir];
                // check for the bound, if within the bound
                // then add them into the result array
                if (r0 < R && c0 < C && r0 >= 0 && c0 >= 0) {
                    result[count] = new int[]{r0, c0};
                    count++;
                }
            }
            k++;
        }
        
        return result;
    }
}