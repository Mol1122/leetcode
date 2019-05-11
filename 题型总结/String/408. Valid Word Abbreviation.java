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
