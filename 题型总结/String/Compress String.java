/* Given a string, replace adjacent, repeated characters with the character followed by the number of 
repeated occurrences. If the character does not has any adjacent, repeated occurrences, it is not changed.

Assumptions

The string is not null

The characters used in the original string are guaranteed to be ‘a’ - ‘z’

Examples

“abbcccdeee” → “ab2c3de3”

 */

public class Solution {
  public String compress(String s) {
    if (s == null || s.length() == 0) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    char[] sc = s.toCharArray();
    int i = 0, j = 0;

    while (j < sc.length) {
      while (j < sc.length && sc[j] == sc[i]) {
        j++;
      }
      if (j - i == 1) {
        sb.append(sc[i] + "");
      } else {
        sb.append(sc[i] + "");
        sb.append((j - i) + "");
      }
      i = j;
    }
    return sb.toString();
  }
}
//time: O(n), space: O(n)