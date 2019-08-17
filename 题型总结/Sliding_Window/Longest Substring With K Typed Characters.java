/* Given a string, return the longest contiguous substring that contains exactly k type of characters.

Return null if there does not exist such substring.

Assumptions:

The given string is not null.
k >= 0.
Examples:

input = "aabcc", k = 3, output = "aabcc".
input = "aabcccc", k = 2, output = "bcccc". */

public class Solution {
  public String longest(String s, int k) {
        if (s == null || s.length() < k) {
            return null;
        }
        if (k == 0) {
            return "";
        }
        int max = 0;
        String maxStr = "";
        int j = 0;
        Map<Character, Integer> map = new HashMap<>();
        char[] sc = s.toCharArray();
        
        for (int i = 0; i < sc.length; i++) {
            while (j < sc.length && (map.size() < k || map.size() == k && map.containsKey(sc[j]))) { //易错
                map.putIfAbsent(sc[j], 0);
                map.put(sc[j], map.get(sc[j]) + 1);
                j++;
            }
            if (j - i > max && map.size() == k) {
                max = j - i;
                maxStr = s.substring(i, j);
            }
            if (map.containsKey(sc[i])) {
                map.put(sc[i], map.get(sc[i]) - 1);
                if (map.get(sc[i]) == 0) {
                    map.remove(sc[i]);
                }
            }
        }
        return maxStr.equals("") ? null : maxStr;
    }
}
//time: O(n * k), space: O(n)