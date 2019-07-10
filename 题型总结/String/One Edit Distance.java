/* Determine if two given Strings are one edit distance.

One edit distance means you can only insert one character/delete one character/replace one character to another character in one of the two given Strings and they will become identical.

Assumptions:

The two given Strings are not null
Examples:

s = "abc", t = "ab" are one edit distance since you can remove the trailing 'c' from s so that s and t are identical

s = "abc", t = "bcd" are not one edit distance */

public class Solution {
  public boolean oneEditDistance(String s, String t) {
    if (s == null || t == null) {
      return false;
    }
    if (s.length() > t.length()) {
      return oneEditDistance(t, s);
    }
    int diff = t.length() - s.length();
    if (diff > 1) {
      return false;
    }
    char[] sc = s.toCharArray();
    char[] tc = t.toCharArray();
    if (diff == 0) {
      int count = 0;
      for (int i = 0; i < sc.length; i++) {
        if (sc[i] != tc[i]) {
          count++;
        }
      }
      return count == 1;
    }
    if (diff == 1) {
      for (int i = 0; i < sc.length; i++) {
        if (sc[i] != tc[i]) {
          return s.substring(i).equals(t.substring(i + 1));
        }
      }
    }
    return true;
  }
}
//time: O(n + m), space: O(n + m)