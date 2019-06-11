/* Given a string, return the shortest contiguous substring that contains exactly k type of characters.

Return an empty string if there does not exist such substring.

Assumptions:

The given string is not null.
k >= 0.
Examples:

input = "aabcc", k = 3, output = "abc".
input = "aabbbcccc", k = 3, output = "abbbc".
input = "aabcc", k = 4, output = "". */

public class Solution {
  public String shortest(String s, int k) {
    if (s == null || s.length() == 0 || k == 0) {
        return "";
    }
    char[] sc = s.toCharArray();
    String minStr = "";
    int min = Integer.MAX_VALUE;
    Map<Character, Integer> map = new HashMap<>();

    int j = 0;
    for (int i = 0; i < sc.length; i++) {
        while (j < sc.length && map.size() < k) {
            map.putIfAbsent(sc[j], 0);
            map.put(sc[j], map.get(sc[j]) + 1);
            j++;
        }
        if (map.size() == k) {
            if (j - i < min) {
                min = j - i;
                minStr = s.substring(i, j);
            }
        }
        if (map.containsKey(sc[i])) {
            map.put(sc[i], map.get(sc[i]) - 1);
            if (map.get(sc[i]) == 0) {
                map.remove(sc[i]);
            }
        }
    }
    
    return minStr;
  }
}
//time: O(nk), space: O(n)
