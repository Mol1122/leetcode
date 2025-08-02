/* Word “book” can be abbreviated to 4, b3, b2k, etc. Given a string and an abbreviation, return if the string matches the abbreviation.

Assumptions:

The original string only contains alphabetic characters.
Both input and pattern are not null.
Pattern would not contain invalid information like "a0a","0".
Examples:

pattern “s11d” matches input “sophisticated” since “11” matches eleven chars “ophisticate”. */

public class Solution {
  public boolean match(String input, String pattern) {
      if (input == null || pattern == null) {
          return false;
      }
      char[] sc = input.toCharArray();
      char[] tc = pattern.toCharArray();
      int i = 0, j = 0;
      
      while (i < sc.length && j < tc.length) {
          if (Character.isDigit(tc[j])) {
              if (tc[j] == 0) {
                  return false;
              }
              int num = 0;
              while (j < tc.length && Character.isDigit(tc[j])) {
                  num = num * 10 + tc[j] - '0';
                  j++;
              }
              i += num;
          } else {
              if (sc[i++] != tc[j++]) {
                  return false;
              }
          }
      }
      return i == sc.length && j == tc.length;
  }
}
//time: O(n), space: O(n)
