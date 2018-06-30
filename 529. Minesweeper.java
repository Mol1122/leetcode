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