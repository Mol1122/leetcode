/* Given a string in compressed form, decompress it to the original string. The adjacent repeated characters in the original string are compressed to have the character followed by the number of repeated occurrences. If the character does not have any adjacent repeated occurrences, it is not compressed.

Assumptions

The string is not null

The characters used in the original string are guaranteed to be ‘a’ - ‘z’

There are no adjacent repeated characters with length > 9

Examples

“acb2c4” → “acbbcccc”

 */

public class Solution {
  public String decompress(String s) {
    if (s == null || s.length() == 0) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    char[] sc = s.toCharArray();
    for (int i = 0; i < sc.length; i++) {
      if (Character.isLetter(sc[i])) {
        sb.append(sc[i] + "");
      } else {
        int count = sc[i] - '0';
        char c = sb.charAt(sb.length() - 1);
        for (int j = 0; j < count - 1; j++) {
          sb.append(c + "");
        }
      }
    }
    return sb.toString();
  }
}
//time: O(n), space: O(n)