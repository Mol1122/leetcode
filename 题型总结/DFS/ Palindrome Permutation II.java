/* Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return []. */
public class Solution {
  public List<String> generatePalindromes(String s) {
      Set<String> results = new HashSet<>();
      if (s == null || s.length() == 0) {
          return new ArrayList<>();
      }
      dfs(s.toCharArray(), 0, results);
      List<String> list = new ArrayList<>();
      for (String str :  results) {
          list.add(str);
      }
      return list;
  }
  
  private void dfs(char[] sc, int index, Set<String> results) {
      if (index == sc.length) {
          if (isPalindrome(new String(sc))) {
              results.add(new String(sc));
          }
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
  
  private boolean isPalindrome(String s) {
      int start = 0, end = s.length() - 1;
      char[] sc = s.toCharArray();
    
      while (start < end) {
          if (sc[start] != sc[end]) {
              return false;
          }
          start++;
          end--;
      }
      return true;
  }
}

/* 时间复杂度：O(n!)
** 空间复杂度：O(n)*/
