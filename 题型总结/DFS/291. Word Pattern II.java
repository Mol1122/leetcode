/* Given a pattern and a string s, return true if s matches the pattern.

A string s matches a pattern if there is some bijective mapping of single characters to non-empty strings such that if each character in pattern is replaced by the string it maps to, then the resulting string is s. A bijective mapping means that no two characters map to the same string, and no character maps to two different strings.

 

Example 1:

Input: pattern = "abab", s = "redblueredblue"
Output: true
Explanation: One possible mapping is as follows:
'a' -> "red"
'b' -> "blue"
Example 2:

Input: pattern = "aaaa", s = "asdasdasdasd"
Output: true
Explanation: One possible mapping is as follows:
'a' -> "asd"
Example 3:

Input: pattern = "aabb", s = "xyzabcxzyabc"
Output: false */

//Method 1
class Solution {
    public boolean wordPatternMatch(String pattern, String s) {
        if (pattern == null || s == null) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return dfs(pattern, s, map, set);
    }

    private boolean dfs(String pattern, String s, Map<Character, String> map, Set<String> set) {
        if (pattern.length() == 0) {
            return s.length() == 0;
        }
        char p = pattern.charAt(0);
        
        if (map.containsKey(p)) {
            if (!s.startsWith(map.get(p))) {
                return false;
            }
            return dfs(pattern.substring(1), s.substring(map.get(p).length()), map, set); 
        }

        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(0, i + 1);
            if (set.contains(sub)) {
                continue;
            }
            set.add(sub);
            map.put(p, sub);
            if (dfs(pattern.substring(1), s.substring(i + 1), map, set)) {
                return true;
            }
            map.remove(p);
            set.remove(sub);
        }
        return false;
    }
}
//time: O(s.length() ^s.length()), space: O(s.length())

