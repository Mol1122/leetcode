/* You start at the cell (rStart, cStart) of an rows x cols grid facing east. The northwest corner is at the first row and column in the grid, and the southeast corner is at the last row and column.

You will walk in a clockwise spiral shape to visit every position in this grid. Whenever you move outside the grid's boundary, we continue our walk outside the grid (but may return to the grid boundary later.). Eventually, we reach all rows * cols spaces of the grid.

Return an array of coordinates representing the positions of the grid in the order you visited them.

 

Example 1:


Input: rows = 1, cols = 4, rStart = 0, cStart = 0
Output: [[0,0],[0,1],[0,2],[0,3]]
Example 2:


Input: rows = 5, cols = 6, rStart = 1, cStart = 4
Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
 */

 class Solution {
    public int[][] spiralMatrixIII(int n, int m, int x, int y) {
        if (n <= 0 || m <= 0) {
            return new int[0][0];
        }
        int[][] result = new int[n * m][2];
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int dir = 0, step = 0, count = 1;

        result[0] = new int[]{x, y};

        while (count < n * m) {
            if (dir == 0 || dir == 2) { //before moving to right or left, increase the current step. 
                step++;
            }
            for (int i = 0; i < step; i++) {
                x += dx[dir];
                y += dy[dir];

                if (x < 0 || x >= n || y < 0 || y >= m) {
                    continue;
                }
                result[count++] = new int[]{x, y};
            }
            dir = (dir + 1) % 4;
        }
        return result;
    }
}
//time: O(max(n, m)^2), sapce: O(1)