/* Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:

pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters. */

public class Solution {
  public boolean wordPatternMatch(String pattern, String str) {
    if (pattern == null || str == null) {
      return false;
    }
    Map<Character, String> map = new HashMap<>();
    Set<String> set = new HashSet<>();
    return match(pattern, str, map, set);
  }

  private boolean match(String pattern, String str, Map<Character, String> map, Set<String> set) {
    if (pattern.length() == 0) {
      return str.length() == 0;
    }
    char c = pattern.charAt(0);
    if (map.containsKey(c)) {
      if (!str.startsWith(map.get(c))) {
        return false;
      }
      return match(pattern.substring(1), str.substring(map.get(c).length()), map, set);
    }
    for (int i = 0; i < str.length(); i++) {
      String sub = str.substring(0, i + 1);
      if (set.contains(sub)) { //这个sub已经match上其他的c了
        continue;
      }
      map.put(c, sub);
      set.add(sub);
      if (match(pattern.substring(1), str.substring(i + 1), map, set)) {
        return true;
      }
      map.remove(c);
      set.remove(sub);
    }
    return false;
  }
}
//time: O(str.length() ^ pattern.length()), space: O(pattern.length())