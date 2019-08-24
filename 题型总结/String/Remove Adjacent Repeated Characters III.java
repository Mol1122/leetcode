/* Remove adjacent, repeated characters in a given string, leaving no character for each group of such characters. The characters in the string are sorted in ascending order.

Assumptions

Try to do it in place.
Examples

“aaaabbbc” is transferred to “c”
Corner Cases

If the given string is null, we do not need to do anything. */

public class Solution {
  public String deDup(String s) {
    if (s == null || s.length() <= 1) {
        return s;
    }
    int i = 0;
    char[] sc = s.toCharArray();
    boolean flag = false;

    for (int j = 1; j < sc.length; j++) {
        if (sc[j] == sc[i]) {
            flag = true;
        } else if (flag == true) {
            sc[i] = sc[j];
            flag = false;
        } else {
            sc[++i] = sc[j];
        }
    }
    sc = Arrays.copyOf(sc, flag ? i : i + 1);
    return new String(sc);
  }
}
//time: O(n), space: O(n)
//只需要把一个group里重复的全部删除即可，不需要recursively do that