/* Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Example 1:

Input: pattern = "abba", str = "dog cat cat dog"
Output: true
Example 2:

Input:pattern = "abba", str = "dog cat cat fish"
Output: false
Example 3:

Input: pattern = "aaaa", str = "dog cat cat dog"
Output: false
Example 4:

Input: pattern = "abba", str = "dog dog dog dog"
Output: false */

class Solution {
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || str == null) {
            return false;
        }
        char[] chars = pattern.toCharArray();
        String[] strs = str.split(" ");
        Map<Character, String> map = new HashMap<>();
        if (chars.length != strs.length) {
            return false;
        }
        
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                if (!map.get(chars[i]).equals(strs[i])) {
                    return false;
                }
                continue;
            }
            if (map.containsValue(strs[i])) { //之前存在一个c 对应strs[i]
                return false;
            }
            map.put(chars[i], strs[i]);
        }
        return true;
    }
}
//time: O(n), space: O(n)