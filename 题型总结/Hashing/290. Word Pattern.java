/* Given a pattern and a string s, find if s follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s. Specifically:

Each letter in pattern maps to exactly one unique word in s.
Each unique word in s maps to exactly one letter in pattern.
No two letters map to the same word, and no two words map to the same letter.
 

Example 1:

Input: pattern = "abba", s = "dog cat cat dog"

Output: true

Explanation:

The bijection can be established as:

'a' maps to "dog".
'b' maps to "cat".
Example 2:

Input: pattern = "abba", s = "dog cat cat fish"

Output: false

Example 3:

Input: pattern = "aaaa", s = "dog cat cat dog"

Output: false */

//Method 1
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
            if (map.containsValue(strs[i])) { //易漏
                return false;
            }
            map.put(chars[i], strs[i]);
        }
        return true;
    }
}

/* 算法：利用hashMap进行一句一句的对应
** 难点：21-23易漏 */

//Method 2
class Solution {
    public boolean wordPattern(String pattern, String s) {
        if (pattern == null || s == null) {
            return false;
        }
        char[] pc = pattern.toCharArray();
        String[] strs = s.split(" ");
        Map<Character, String> map = new HashMap<>();

        if (pc.length != strs.length) {
            return false;
        }
        for (int i = 0; i < pc.length; i++) {
            if (map.containsKey(pc[i]) && !map.get(pc[i]).equals(strs[i])) {
                return false;
            }
            if (!map.containsKey(pc[i]) && map.containsValue(strs[i])) {
                return false;
            }
            map.put(pc[i], strs[i]);
        }
        return true;
    }
}