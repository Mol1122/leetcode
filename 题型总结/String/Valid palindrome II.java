/* Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.


Example 1:

Input: "aba"
Output: True
Example 2:

Input: "abca"
Output: True */

public class Solution {
  public boolean validPalindrome(String s) {
    if (s == null || s.length() == 0) {
      return true;
    }
    int left = 0, right = s.length() - 1;
    while (left <= right) {
      if (s.charAt(left) != s.charAt(right)) {
        break;
      }
      left++;
      right--;
    }
    return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
  }

  private boolean isPalindrome(String s, int start, int end) {
    while (start < end) {
      if (s.charAt(start) != s.charAt(end)) {
        return false;
      }
      start++;
      end--;
    }
    return true;
  }
}
//time: O(n), space: O(1)