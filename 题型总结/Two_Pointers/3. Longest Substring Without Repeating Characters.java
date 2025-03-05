/* Given a string s, find the length of the longest substring without duplicate characters.

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring. */

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int j = 0;
        char[] sc = s.toCharArray();
        int max = 0;
        
        for (int i = 0; i < sc.length; i++) {
            while (j < sc.length && !set.contains(sc[j])) {
                set.add(sc[j]);
                j++;
            }
            max = Math.max(max, j - i);
            if (set.contains(sc[i])) {
                set.remove(sc[i]);
            }
        }
        return max;
    }
}
//time: O(n), space: O(n)