/* Remove given characters in input string, the relative order of other characters should be remained. Return the new string after deletion.

Assumptions

The given input string is not null.
The characters to be removed is given by another string, it is guaranteed to be not null.
Examples

input = "abcd", t = "ab", delete all instances of 'a' and 'b', the result is "cd". */

public class Solution {
  public String remove(String input, String t) {
      if (input == null || t == null || 
          input.length() == 0 || t.length() == 0) {
          return input;
      }
      char[] sc = input.toCharArray();
      int left = 0, right = 0;
      
      while (right < sc.length) {
          if (t.indexOf(sc[right]) == -1) {
              char c = sc[left];
              sc[left] = sc[right];
              sc[right] = c;
              left++;
          } 
          right++;
      }
      return new String(sc, 0, left);
  }
}

/* 时间复杂度：O(n)
** 空间复杂度：O(n) */
