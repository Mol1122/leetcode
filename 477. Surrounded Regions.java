public class Solution {
    /*
     * @param board: board a 2D board containing 'X' and 'O'
     * @return: nothing
     */
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