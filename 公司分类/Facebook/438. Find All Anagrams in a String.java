/* Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab". */

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> results = new ArrayList<>();
        if (s == null || p == null) {
            return results;
        }
        Map<Character, Integer> map = getCount(p);
        int match = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char right = s.charAt(i);
            Integer count = map.get(right);
            if (count != null) {
                map.put(right, count - 1);
                if (count == 1) {
                    match++;
                }
            }
            if (i >= p.length()) {
                char left = s.charAt(i - p.length());
                count = map.get(left);
                if (count != null) {
                    map.put(left, count + 1);
                    if (count == 0) {
                        match--;
                    }
                }
            }
            if (match == map.size()) {
                results.add(i - p.length() + 1);
            }
        }
        return results;
    }
    
    private Map<Character, Integer> getCount(String p) {
        Map<Character, Integer> map = new HashMap<>();
        
        for (char c : p.toCharArray()) {
            map.putIfAbsent(c, 0);
            map.put(c, map.get(c) + 1);
        }
        return map;
    }
}
//time: O(n), space: O(n)