/* Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd". */

public class Solution {
  public String shortestPalindrome(String s) {
    if (s == null || s.length() == 0) {
      return "";
    }
    int j = 0;
    for (int i = s.length() - 1; i >= 0; i--) {
      if (s.charAt(i) == s.charAt(j)) {
        j++;
      }
    }
    if (j == s.length()) {
      return s;
    }
    String suffix = s.substring(j);
    String prefix = new StringBuilder(suffix).reverse().toString();
    String mid = shortestPalindrome(s.substring(0, j));
    return prefix + mid + suffix;
  }
}
//time: O(n^2), space: O(n)