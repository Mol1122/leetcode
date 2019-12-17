/* Find the length of the longest substring T of a given string (consists of lowercase letters only) such 
that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times. */

class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() < k) {
            return 0;
        }   
        if (k <= 1) {
            return s.length();
        }
        Map<Character, Integer> ch2count = new HashMap<>();
        for (char c : s.toCharArray()) {
            ch2count.putIfAbsent(c, 0);
            ch2count.put(c, ch2count.get(c) + 1);
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            if (ch2count.get(s.charAt(i)) < k) {
                sb.setCharAt(i, ',');
            }
        }
        String[] strs = sb.toString().split(",");
        if (strs.length == 1) {
            return strs[0].length();
        }
        int max = 0;
        for (String str : strs) {
            max = Math.max(max, longestSubstring(str, k)); //因为不确定被间隔出来的是不是就是真的连续的valid substring
        }
        return max;
    }
}
//time: O(n), space: O(n)