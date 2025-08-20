/* Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].

Note:
You may assume that all inputs are consist of lowercase letters a-z. */

//Method 1
public class Solution {
  public List<String> findWords(char[][] board, String[] words) {
    Set<String> results = new HashSet<>();
    if (board == null || board.length == 0 || words == null || words.length == 0) {
      return new ArrayList<>();
    }
    Map<String, Boolean> prefixIsWord = getMap(words);
    int n = board.length;
    int m = board[0].length;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        boolean[][] visited = new boolean[n][m];
        dfs(board, visited, i, j, prefixIsWord, board[i][j] + "", results);
      }
    }
    return new ArrayList<>(results);
  }

  private void dfs(char[][] board, boolean[][] visited, int x, int y,
                   Map<String, Boolean> prefixIsWord, String temp, Set<String> results) {
    if (!prefixIsWord.containsKey(temp)) {
      return;
    }
    if (prefixIsWord.get(temp)) {
      results.add(new String(temp));
    }
    visited[x][y] = true;
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length && !visited[nx][ny]) {
        visited[nx][ny] = true;
        dfs(board, visited, nx, ny, prefixIsWord, temp + board[nx][ny], results);
        visited[nx][ny] = false;
      }
    }
  }

  private Map<String, Boolean> getMap(String[] words) {
    Map<String, Boolean> map = new HashMap<>();
    for (String word : words) {
      for (int i = 1; i < word.length(); i++) {
        String sub = word.substring(0, i);
        map.putIfAbsent(sub, false);
      }
      map.put(word, true);
    }
    return map;
  }
}


//Method 2, 会TLE
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> results = new HashSet<>();
        if (board == null || board.length == 0) {
            return new ArrayList<>();
        }
        Map<String, Boolean> prefixIsWord = getMap(words);
        int n = board.length;
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, visited, prefixIsWord, new StringBuilder(), results);
            }
        }
        return new ArrayList<>(results);
    }

    private void dfs(char[][] board, int x, int y, boolean[][] visited, Map<String, Boolean> prefixIsWord, StringBuilder sb, Set<String> results) {
        if (sb.toString().length() != 0 && !prefixIsWord.containsKey(sb.toString())) {
            return;
        }
        if (sb.toString().length() != 0 && prefixIsWord.get(sb.toString())) {
            results.add(new String(sb));
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return;
        }
        if (visited[x][y]) {
            return;
        }
        sb.append(board[x][y]);
        visited[x][y] = true;

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            dfs(board, nx, ny, visited, prefixIsWord, sb, results);
        }
        visited[x][y] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    private Map<String, Boolean> getMap(String[] words) {
        Map<String, Boolean> map = new HashMap<>();

        for (String word : words) {
            String prefix = "";
            for (char c : word.toCharArray()) {
                prefix += c;
                map.putIfAbsent(prefix, false);
            }
            map.put(word, true);
        }
        return map;
    }
}
