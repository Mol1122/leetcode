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
  
  private void dfs(char[] sc, int index, StringBuilder sb, List<String> results) {
      if (index == sc.length) {
          results.add(sb.toString());
          return;
      }
      //这一层选什么
      sb.append(sc[index]);
      dfs(sc, index + 1, sb, results);
      sb.deleteCharAt(sb.length() - 1);
    
      //skip the duplicated chars
      while (index < sc.length - 1 && sc[index] == sc[index + 1]) {
          index++;
      }
      dfs(sc, index + 1, sb, results);
  }
}
//time: O(2^n), spance: O(n)