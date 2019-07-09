/* Reverse the words in a sentence and truncate all heading/trailing/duplicate space characters.

Examples

“ I  love  Google  ” → “Google love I”

Corner Cases

If the given string is null, we do not need to do anything. */

public class Solution {
  public String reverseWords(String s) {
    if (s == null || s.length() == 0) {
      return s;
    }
    s = s.trim();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if (sb.length() != 0 && sb.charAt(sb.length() - 1) == ' ' && s.charAt(i) == ' ') {
        continue;
      }
      sb.append(s.charAt(i));
    }
    char[] sc = sb.toString().toCharArray();
    reverse(sc, 0, sc.length - 1);

    int index = 0;
    for (int i = 0; i < sc.length; i++) {
      if (sc[i] == ' ') {
        reverse(sc, index, i - 1);
        index = i + 1;
      }
    }
    reverse(sc, index, sc.length - 1);
    return new String(sc);
  }

  private void reverse(char[] sc, int start, int end) {
    while (start < end) {
      char c = sc[start];
      sc[start] = sc[end];
      sc[end] = c;
      start++;
      end--;
    }
  }
}
//time: O(n^2), space: O(n)