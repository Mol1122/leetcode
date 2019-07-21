/* Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ). 

Examples:

"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""] */

public class Solution {
  public List<String> removeInvalidParentheses(String s) {
    List<String> results = new ArrayList<>();
    if (s == null) {
      return results;
    }
    int[] count = getCount(s); //get the #left, #right that needs to be deleted
    dfs(s, 0, count[0], count[1], results);
    return results;
  }

  private void dfs(String s, int startIndex, int leftCount, int rightCount, List<String> results) {
    if (leftCount == 0 && rightCount == 0 && isValid(s)) {
      results.add(new String(s));
      return;
    }
    for (int i = startIndex; i < s.length(); i++) {
      if (i > startIndex && s.charAt(i) == s.charAt(i - 1)) {
        continue;
      }
      char c = s.charAt(i);
      if (leftCount > 0 && c == '(') {
        dfs(s.substring(0, i) + s.substring(i + 1), i, leftCount - 1, rightCount, results);
      }
      if (rightCount > 0 && c == ')') {
        dfs(s.substring(0, i) + s.substring(i + 1), i, leftCount, rightCount - 1, results);
      }
    }
  }

  private boolean isValid(String s) {
    int[] count = getCount(s);
    return count[0] == 0 && count[1] == 0;
  }

  private int[] getCount(String s) {
    int[] count = new int[2];

    for (char c : s.toCharArray()) {
      if (c == '(') {
        count[0]++;
      } else if (c == ')') {
        if (count[0] > 0) {
          count[0]--;
        } else {
          count[1]++;
        }
      }
    }
    return count;
  }
}
//time: O(n^n * n), space: O(n)