/* Given a set of characters represented by a String, return a list containing all subsets of the characters.

Assumptions

There are no duplicate characters in the original set.
​Examples

Set = "abc", all the subsets are [“”, “a”, “ab”, “abc”, “ac”, “b”, “bc”, “c”]
Set = "", all the subsets are [""]
Set = null, all the subsets are [] */

public class Solution {
  public List<String> subSets(String s) {
      List<String> results = new ArrayList<>();
      if (s == null) {
          return results;
      }
      dfs(s, 0, new StringBuilder(), results);
      return results;
  }
  
  private void dfs(String s, int startIndex, StringBuilder temp, List<String> results) {
      if (startIndex == s.length()) {
          results.add(new String(temp.toString()));
          return;
      }
      temp.append(s.charAt(startIndex));
      dfs(s, startIndex + 1, temp, results);
      temp.deleteCharAt(temp.length() - 1);
    
      dfs(s, startIndex + 1, temp, results);
  }
}

/* 时间复杂度：O(2^n)
** 空间复杂度：O(n)
            {}
          /     \
         {a}    {}
      /    \
    {ab}    {a}
    /     \
  {abc}   {ab}

*/
