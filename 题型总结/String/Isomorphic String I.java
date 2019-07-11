/* Two Strings are called isomorphic if the letters in one String can be remapped to get the second String. Remapping a letter means replacing all occurrences of it with another letter. The ordering of the letters remains unchanged. The mapping is two way and no two letters may map to the same letter, but a letter may map to itself. Determine if two given String are isomorphic.

Assumptions:

The two given Strings are not null.
Examples:

"abca" and "xyzx" are isomorphic since the mapping is 'a' <-> 'x', 'b' <-> 'y', 'c' <-> 'z'.

"abba" and "cccc" are not isomorphic. */

public class Solution {
  public boolean isomorphic(String s, String t) {
    if (s == null || t == null || s.length() != t.length()) {
      return false;
    }
    char[] sc = s.toCharArray();
    char[] tc = t.toCharArray();

    Map<Character, Character> map1 = new HashMap<>();
    for (int i = 0; i < sc.length; i++) {
      if (!map1.containsKey(sc[i])) {
        map1.put(sc[i], tc[i]);
      } else {
        if (map1.get(sc[i]) != tc[i]) {
          return false;
        }
      }
    }
    Map<Character, Character> map2 = new HashMap<>();
    for (int i = 0; i < tc.length; i++) {
      if (!map2.containsKey(tc[i])) {
        map2.put(tc[i], sc[i]);
      } else {
        if (map2.get(tc[i]) != sc[i]) {
          return false;
        }
      }
    }
    return true;
  }
}
//time: O(n), space: O(n)
