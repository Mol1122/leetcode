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
    if (s == null || s.length() == 0) {
        return "";
    }
    String minStr = "";
    int min = Integer.MAX_VALUE;
    Map<Character, Integer> map = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
        for (int j = i; j < s.length(); j++) {
            if (map.size() < k || map.size() == k && map.containsKey(s.charAt(j))) {
                map.putIfAbsent(s.charAt(j), 0);
                map.put(s.charAt(j), map.get(s.charAt(j) + 1));
                if (map.size() == k && min > j - i + 1) {
                    min = j - i + 1;
                    minStr = s.substring(i, j + 1);
                }
            }
        }
        map = new HashMap<>();
    }
    return minStr;
  }
}
//难点：j指针必须要回溯，因为当删掉i的时候，长度有可能变为更短
//time: O(n^2*k), space: O(n)
