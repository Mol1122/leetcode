/* Given a set of characters represented by a String, return a list containing all subsets of the characters whose size is K.

Assumptions

There are no duplicate characters in the original set.

Examples

Set = "abc", K = 2, all the subsets are [“ab”, “ac”, “bc”].

Set = "", K = 0, all the subsets are [""].

Set = "", K = 1, all the subsets are []. */

public class Solution {
  public List<String> subSetsOfSizeK(String s, int k) {
    List<String> results = new ArrayList<>();
    if (s == null) {
      return results;
    }
    dfs(s, 0, new StringBuilder(), k, results);
    return results;
  }

  private void dfs(String s, int index, StringBuilder temp, int k, List<String> results) {
    if (index == s.length()) {
      if (temp.length() == k) {
        results.add(new String(temp.toString()));
      }
      return;
    }
    temp.append(s.charAt(index));
    dfs(s, index + 1, temp, k, results);
    temp.deleteCharAt(temp.length() - 1);

    dfs(s, index + 1, temp, k, results);
  }
}
//time: O(2^n), space: O(n)