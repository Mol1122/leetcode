public class Solution {
    /**
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean result = dfs(board, i, j, word, 0);
                    if (result) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int x, int y, String word, int start) {
        
        if (start == word.length()) {
            return true;
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        if (word.charAt(start) != board[x][y]) {
            return false;
        }
        
        board[x][y] = '#';
        boolean result = dfs(board, x - 1, y, word, start + 1) ||
                         dfs(board, x + 1, y, word, start + 1) ||
                         dfs(board, x, y - 1, word, start + 1) ||
                         dfs(board, x, y + 1, word, start + 1);
        board[x][y] = word.charAt(start); //易漏
        return result;
    }
}

/* 算法：基本的dfs搜索类算法
** 难点：board[x][y]在最后要变回原来的character */