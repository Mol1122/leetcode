public class Solution {
  public List<String> permutations(String s) {
      List<String> results = new ArrayList<>();
      if (s == null || s.length() == 0) {
          return results;
      }
      dfs(s.toCharArray(), 0, results);
      return results;
  }
  
  private void dfs(char[] sc, int index, List<String> results) {
      if (index == sc.length) {
          results.add(new String(sc));
          return;
      }
      for (int i = index; i < sc.length; i++) {
          swap(sc, index, i);
          dfs(sc, index + 1, results);
          swap(sc, index, i);
      }
  }
  
  private void swap(char[] sc, int i, int j) {
      char c = sc[i];
      sc[i] = sc[j];
      sc[j] = c;
  }
}

/* 时间复杂度：O(n!)
** 空间复杂度：O(n) */
