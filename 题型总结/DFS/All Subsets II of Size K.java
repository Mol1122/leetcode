/* Given a set of characters represented by a String, return a list containing all subsets of the characters whose size is K. Notice that each subset returned will be sorted for deduplication.



Assumptions

There could be duplicate characters in the original set.

​

Examples

Set = "abc", K = 2, all the subsets are [“ab”, “ac”, “bc”].

Set = "abb", K = 2, all the subsets are [“ab”, “bb”].

Set = "abab", K = 2, all the subsets are [“aa”, “ab”, “bb”].

Set = "", K = 0, all the subsets are [""].

Set = "", K = 1, all the subsets are [].

Set = null, K = 0, all the subsets are []. */

public class Solution {
  public List<String> subSetsIIOfSizeK(String s, int k) {
    List<String> results = new ArrayList<>();
    if (s == null) {
      return results;
    }
    char[] sc = s.toCharArray();
    Arrays.sort(sc);
    dfs(sc, 0, k, new StringBuilder(), results);
    return results;
  }

  private void dfs(char[] sc, int index, int k, StringBuilder sb, List<String> results) {
    if (index == sc.length) {
      if (sb.length() == k) {
        results.add(new String(sb.toString()));
      }
      return;
    }
    sb.append(sc[index]);
    dfs(sc, index + 1, k, sb, results);
    sb.deleteCharAt(sb.length() - 1);

    while (index < sc.length - 1 && sc[index] == sc[index + 1]) {
      index++;
    }
    dfs(sc, index + 1, k, sb, results);
  }
}
//time: O(2^n), space: O(n)