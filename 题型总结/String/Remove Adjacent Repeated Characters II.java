/* Remove adjacent, repeated characters in a given string, leaving only two characters for each group of such characters. The characters in the string are sorted in ascending order.

Assumptions

Try to do it in place.
Examples

“aaaabbbc” is transferred to “aabbc”
Corner Cases

If the given string is null, we do not need to do anything. */
public class Solution {
  public String deDup(String s) {
    if (s == null || s.length() <= 1) {
        return s;
    }
    char[] sc = s.toCharArray();
    int i = 1;
    for (int j = 2; j < sc.length; j++) {
        if (sc[j] != sc[i - 1]) {
            sc[++i] = sc[j];
        }
    }
    sc = Arrays.copyOf(sc, i + 1);
    return new String(sc);
  }
}
//time: O(n), space: O(n)
