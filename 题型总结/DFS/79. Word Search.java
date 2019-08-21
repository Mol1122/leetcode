/* Given a 2D board and a word, find if the word exists in the grid.The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Input: board = [[“ABCE”],
                [“SFCS”],
                [“ADEE”]
                ]
Output: Word = “ABCCED”   return true
            Word = “SEE”      return true
            Word = “ABCB”      return false  */

public class Solution {
  public boolean isWord(char[][] board, String word) {
    if (board == null || board.length == 0 || word == null || word.length() == 0) {
        return false;
    }
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            if (board[i][j] == word.charAt(0)) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                visited[i][j] = true;
                if (dfs(board, word, visited, i, j, 0)) {
                    return true;
                }
            }
        }
    }
    return false;
  }

  private boolean dfs(char[][] board, String word, boolean[][] visited, 
                      int x, int y, int index) {
    if (index == word.length()) {
        return true;
    }
    if (word.charAt(index) != board[x][y]) {
        return false;
    }
    visited[x][y] = true;
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length && !visited[nx][ny]) {
            if (dfs(board, word, visited, nx, ny, index + 1)) {
                return true;
            }
        }
    }
    return false;
  }
}
//time: O(n^2 * 4^word.length()), space: O(n^2)