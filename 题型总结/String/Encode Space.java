/* In URL encoding, whenever we see an space " ", we need to replace it with "20%". Provide a method that performs this encoding for a given string.

Examples

"google/q?flo wer market" → "google/q?flo20%wer20%market"
Corner Cases

If the given string is null, we do not need to do anything. */

public class Solution {
  public String encode(String s) {
    if (s == null || s.length() == 0) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
      if (c == ' ') {
        sb.append("20%");
      } else {
        sb.append(c + "");
      }
    }
    return sb.toString();
  }
}
//time: O(n), space: O(1)