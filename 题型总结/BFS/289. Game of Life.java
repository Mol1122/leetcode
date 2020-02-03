/* According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton 
devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts 
with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the 
above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state. 
The next state is 
created by applying the above rules simultaneously to every cell in the current state, where births and 
deaths occur simultaneously.

Example:

Input: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
Follow up:

Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update 
some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would 
cause problems when the active area encroaches the border of the array. How would you address these problems? */

class Solution {
    public void gameOfLife(int[][] board) {
         if (board == null || board.length == 0) {
             return;
         }
         int n = board.length;
         int m = board[0].length;
         
         for (int i = 0; i < n; i++) {
             for (int j = 0; j < m; j++) {
                 int count = countLive(board, i, j);
                 
                 if (board[i][j] == 1 && (count == 2 || count == 3)) {
                     board[i][j] = 3;
                 }
                 if (board[i][j] == 0 && count == 3) {
                     board[i][j] = 2;
                 }
             }
         }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] >>= 1;
            }
        }
     }
    
    private int countLive(int[][] board, int x, int y) {
        int[] dx = {1, 0, -1, 0, -1, -1, 1, 1};
        int[] dy = {0, 1, 0, -1, -1, 1, -1, 1};
        int count = 0;
        
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length) {
                continue;
            }
            count += board[nx][ny] & 1;
        }
        return count;
    }
    
    
    
    
//     public void gameOfLife(int[][] board) {
//         if (board == null || board.length == 0) {
//             return;
//         }
//         int n = board.length;
//         int m = board[0].length;
//         int[][] result = new int[n][m];
        
//         int[] dx = {1, 0, -1, 0, -1, -1, 1, 1};
//         int[] dy = {0, 1, 0, -1, -1, 1, -1, 1};
        
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
//                 int count = 0;
//                 for (int k = 0; k < 8; k++) {
//                     int nx = i + dx[k];
//                     int ny = j + dy[k];
                    
//                     if (!inBound(board, nx, ny)) {
//                         continue;
//                     }
//                     if (board[nx][ny] == 1) {
//                         count++;
//                     }
//                 }
//                 addValue(result, board[i][j], i, j, count);
//             }
//         }
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
//                 board[i][j] = result[i][j];
//             }
//         }
//     }
    
//     private void addValue(int[][] result, int live, int x, int y, int count) {
//         if (count < 2) {
//             result[x][y] = 0;
//         } else if (live == 1 && (count == 2 || count == 3)) {
//             result[x][y] = 1;
//         }  else if (live == 1 && count > 3) {
//             result[x][y] = 0;
//         } else if (live == 0 && count == 3) {
//             result[x][y] = 1;
//         }
//     }
    
//     private boolean inBound(int[][] board, int x, int y) {
//         if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
//             return false;
//         }
//         return true;
//     }
}

/* 算法: in-place的算法要借助bit operation. [2nd state, 1st state], 00, 01, 10, 11
** 难点：判断是否是1， &1, 
**      去掉state 1 >>1*/