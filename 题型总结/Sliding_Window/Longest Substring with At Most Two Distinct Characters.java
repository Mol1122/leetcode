/* Given a string, find the the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”, T is "ece" */

public class Solution {
  public int lengthOfLongestSubstringTwoDistinct(String s) {
    if (s == null || s.length() == 0) {
        return 0;
    }
    char[] sc = s.toCharArray();
    Map<Character, Integer> map = new HashMap<>();
    int max = 0;

    int j = 0;
    for (int i = 0; i < sc.length; i++) {
        while (j < sc.length && (map.size() < 2 || map.size() == 2 && map.containsKey(sc[j]))) {
            map.putIfAbsent(sc[j], 0);
            map.put(sc[j], map.get(sc[j]) + 1);
            j++;
        }
        max = Math.max(max, j - i);
        if (map.containsKey(sc[i])) {
            map.put(sc[i], map.get(sc[i]) - 1);
            if (map.get(sc[i]) == 0) {
                map.remove(sc[i]);
            }
        }
    }
    return max;
  }
}
//time: O(n), space: O(n)