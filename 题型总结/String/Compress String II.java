/* Given a string, replace adjacent, repeated characters with the character followed by the number of repeated occurrences.

Assumptions

The string is not null

The characters used in the original string are guaranteed to be ‘a’ - ‘z’

Examples

“abbcccdeee” → “a1b2c3d1e3” */

public class Solution {
  public String compress(String s) {
      if (s == null || s.length() == 0) {
          return "";
      }
      StringBuilder sb = new StringBuilder();
      int i = 0, j = 0;
    
      while (j < s.length()) {
         while (j < s.length() && s.charAt(j) == s.charAt(i)) {
             j++;
         }
        sb.append(s.charAt(i));
        sb.append(j - i);
        i = j;
      }
     
      return sb.toString();
  }
}
//time: O(n), space: O(1)