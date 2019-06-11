/* Given a string, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3. */

public class Solution {
  public int lengthOfLongestSubstringKDistinct(String s, int k) {
    if (s == null || s.length() == 0) {
        return 0;
    }
    char[] sc = s.toCharArray();
    Map<Character, Integer> map = new HashMap<>();
    int max = 0;

    int j = 0;;
    for (int i = 0; i < sc.length; i++) {
        while (j < sc.length && (map.size() < k || map.size() == k && map.containsKey(sc[j]))) {
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
