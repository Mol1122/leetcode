/* Given an original string input, and two strings S and T, from left to right replace all occurrences of S in input with T.

Assumptions

input, S and T are not null, S is not empty string
Examples

input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
input = "laicode", S = "code", T = "offer", input becomes "laioffer" */

public class Solution {
  public String replace(String input, String source, String target) {
    if (input == null || input.length() == 0) {
      return input;
    }
    StringBuilder sb = new StringBuilder();
    int i = 0;

    while (i < input.length()) {
      if (input.substring(i).startsWith(source)) {
        sb.append(target);
        i += source.length();
      } else {
        sb.append(input.charAt(i));
        i++;
      }
    }
    return sb.toString();
  }
}
//time: O(n), space: O(1)