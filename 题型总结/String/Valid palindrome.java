/* Given a string, determine if it is a palindrome, considering only alphanumeric characters('0'-'9','a'-'z','A'-'Z') and ignoring cases.

For example,
"an apple, :) elp pana#" is a palindrome.

"dia monds dn dia" is not a palindrome. */

public class Solution {
  public boolean valid(String s) {
    if (s == null || s.length() == 0) {
      return true;
    }
    int i = 0, j = s.length() - 1;
    while (i < j) {
      while (i < j && !Character.isDigit(s.charAt(i)) && !Character.isLetter(s.charAt(i))) {
        i++;
      }
      if (i == s.length()) {
        return true;
      }
      while (i < j && !Character.isDigit(s.charAt(j)) && !Character.isLetter(s.charAt(j))) {
        j--;
      }
      if (j < 0) {
        return true;
      }
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      } else {
        i++;
        j--;
      }
    }
    return true;
  }
}
//time: O(n), space: O(1)
