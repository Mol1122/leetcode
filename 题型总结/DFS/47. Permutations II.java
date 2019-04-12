public class Solution {
  public List<String> permutations(String s) {
      List<String> results = new ArrayList<>();
      if (s == null) {
          return results;
      }
      char[] sc = s.toCharArray();
      Arrays.sort(sc);
      dfs(sc, 0, new boolean[sc.length], new StringBuilder(), results);
      return results;
  }
  
  private void dfs(char[] sc, int index, boolean[] visited, 
                   StringBuilder permutation, List<String> results) {
      if (permutation.length() == sc.length) {
          results.add(new String(permutation));
          return;
      }
      for (int i = 0; i < sc.length; i++) {
          if (visited[i]) {
              continue;
          }
          if (i != 0 && sc[i] == sc[i - 1] && !visited[i - 1]) {
              continue;
          }
          permutation.append(sc[i]);
          visited[i] = true;
          dfs(sc, i + 1, visited, permutation, results);
          visited[i] = false;
          permutation.deleteCharAt(permutation.length() - 1);
      }
  }
}

/* 时间复杂度：O(n!)
** 空间复杂度：O(n) */
