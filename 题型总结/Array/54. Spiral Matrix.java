/* Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7] */

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> results = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return results;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        boolean[][] visited = new boolean[n][m];
        int dir = 0;
        int x = 0, y = 0;
        
        for (int i = 0; i < n * m; i++) {
            results.add(matrix[x][y]);
            visited[x][y] = true;
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) {
                dir = (dir + 1) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }
            x = nx;
            y = ny;
        }
        return results;
    }
}
//time: O(n * m), space: O(n * m)