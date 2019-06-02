/* Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "laicode"
return 0.

s = "lovelaicode",
return 2.

Note: You may assume the string contain only lowercase letters. */
public class Solution {
  public int firstUniqChar(String s) {
      if (s == null || s.length() == 0) {
          return -1;
      }
      int[] count = new int[256];
      for (char c : s.toCharArray()) {
          count[c]++;
      }
      for (int i = 0; i < s.length(); i++) {
          if (count[s.charAt(i)] == 1) {
              return i;
          }
      }
      return -1;
  }
}
//time: O(n), space: O(256)