/* Given a string with possible duplicate characters, return a list with all permutations of the characters.

Examples

Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
Set = "aba", all permutations are ["aab", "aba", "baa"]
Set = "", all permutations are [""]
Set = null, all permutations are [] */

public class Solution {
  public List<String> permutations(String s) {
    List<String> results = new ArrayList<>();
    if (s == null) {
        return results;
    }
    char[] sc = s.toCharArray();
    dfs(sc, 0, results);
    return results;
  }

  private void dfs(char[] sc, int index, List<String> results) {
      if (index == sc.length) {
          results.add(new String(sc));
          return;
      }
      Set<Character> set = new HashSet<>();
      for (int i = index; i < sc.length; i++) {
          if (!set.contains(sc[i])) {
              set.add(sc[i]);
              swap(sc, index, i);
              dfs(sc, index + 1, results);
              swap(sc, index, i);
          }
      }
  }

  private void swap(char[] sc, int i, int j) {
      char temp = sc[i];
      sc[i] = sc[j];
      sc[j] = temp;
  }
}
//time: O(n!), space: O(n)


/*public class Solution {
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
}  */

/* 时间复杂度：O(n!)
** 空间复杂度：O(n) */
