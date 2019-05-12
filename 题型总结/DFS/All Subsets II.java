public class Solution {
  public List<String> subSets(String s) {
      List<String> results = new ArrayList<>();
      if (s == null) {
          return results;
      }
      char[] sc = s.toCharArray();
      Arrays.sort(sc);
      dfs(sc, 0, new StringBuilder(), results);
      return results;
  }
  
  private void dfs(char[] sc, int startIndex, StringBuilder temp, List<String> results) {
      results.add(new String(temp.toString()));
    
      for (int i = startIndex; i < sc.length; i++) {
          if (i != 0 && sc[i] == sc[i - 1] && i > startIndex) {
              continue;
          }
          temp.append(sc[i]);
          dfs(sc, i + 1, temp, results);
          temp.deleteCharAt(temp.length() - 1);
      }
  }
}

/* 时间复杂度：O(s.length ^ s.length) */