/* Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

If a mine ('M') is revealed, then the game is over - change it to 'X'.
If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.
 

Example 1:

Input: 

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

Click : [3,0]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

Example 2:

Input: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:
 */

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return board;
        }
        int n = board.length;
        int m = board[0].length;
        int row = click[0], col = click[1];
        int count = 0;
        
        if (board[row][col] == 'M') {
            board[row][col] = 'X';
        } else {
            //count the number of surroundings
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    int cr = row + i;
                    int cc = col + j;
                    if (cr >= 0 && cr < n && cc >= 0 && cc < m && (board[cr][cc] == 'M' || board[cr][cc] == 'X')) {
                        count++;
                    }
                }
            }
            if (count > 0) {
                board[row][col] =(char) (count + '0');
            } else {
                board[row][col] = 'B';
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i == 0 && j == 0) {
                            continue;
                        }
                        int cr = row + i;
                        int cc = col + j;
                        if (cr >= 0 && cr < n && cc >= 0 && cc < m && board[cr][cc] == 'E') {
                            updateBoard(board, new int[]{cr, cc});
                        }
                    }
                }
            }
        }
        return board;
    }
}

/* 算法：DFS

This is a typical Search problem, either by using DFS or BFS. Search rules:

    If click on a mine ('M'), mark it as 'X', stop further search.
    If click on an empty cell ('E'), depends on how many surrounding mine:
    2.1 Has surrounding mine(s), mark it with number of surrounding mine(s), stop further search.
    2.2 No surrounding mine, mark it as 'B', continue search its 8 neighbors.

*/