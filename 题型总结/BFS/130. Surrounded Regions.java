/* Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically. */

//BFS
public class Solution {
    public void surroundedRegions(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int n = board.length;
        int m = board[0].length;
        
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O') {
                bfs(board, i, 0);
            }
            if (board[i][m - 1] == 'O') {
                bfs(board, i, m - 1);
            }
        }
        
        for (int j = 0; j < m; j++) {
            if (board[0][j] == 'O') {
                bfs(board, 0, j);
            }
            if (board[n - 1][j] == 'O') {
                bfs(board, n - 1, j);
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'W') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    private void bfs(char[][] board, int x, int y) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        qx.offer(x);
        qy.offer(y);
        board[x][y] = 'W';
        
        while (!qx.isEmpty()) {
            int cx = qx.poll();
            int cy = qy.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length && board[nx][ny] == 'O') {
                    qx.offer(nx);
                    qy.offer(ny);
                    board[nx][ny] = 'W';
                }
            }
        }
    }
}

/* 思路：找到被平原围绕的盆地
   反向思维-> 找到没有被平原围绕的盆地 -> 从4条边开始注水
   时间复杂度: O(mn) */

//DFS
class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
        	return;
        }
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
        	if (board[i][0] == 'O') {
        		solveHelper(board, i, 0);
        	}
        	if (board[i][m-1] == 'O') {
        		solveHelper(board, i, m-1);
        	}
        }
        for (int j = 0; j < m; j++) {
        	if (board[0][j] == 'O') {
        		solveHelper(board, 0, j);
        	}
        	if (board[n-1][j] == 'O') {
        		solveHelper(board, n-1, j);
        	}
        }
        
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < m; j++) {
        		if (board[i][j] == 'O') {
        			board[i][j] = 'X';
        		} else if (board[i][j] == '#') {
        			board[i][j] = 'O';
        		}
        	}
        }
    }
    
    private void solveHelper(char[][] board, int i, int j) {
		if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
			return;
		}
		if (board[i][j] != 'O') {
			return;
		}
		board[i][j] = '#';
		solveHelper(board, i - 1, j);
		solveHelper(board, i + 1, j);
		solveHelper(board, i, j - 1);
		solveHelper(board, i, j + 1);
	}
}