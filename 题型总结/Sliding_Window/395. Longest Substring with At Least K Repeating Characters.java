/* Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.

if no such substring exists, return 0.


Example 1:

Input: s = "aaabb", k = 3
Output: 3
Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input: s = "ababbc", k = 2
Output: 5
Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times. */

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
        for (int i = 0; i < sb.length(); i++) {
            if (ch2count.get(sb.charAt(i)) < k) {
                sb.setCharAt(i, ',');
            }
        }
        String[] strs = sb.toString().split(",");
        if (strs.length == 1) { //必须要有这个exit condition, 不然会infinite loop. eg. bbaaacbd
            return strs[0].length();
        }
        int max = 0;
        for (String str : strs) {
            max = Math.max(max, longestSubstring(str, k));
        }
        return max;
    }
}
//time: O(n), space: O(n)
//算法难点：因为题目要求至少k个重复的character,那么最后的结果中不可能包含少于k个的character。 所以可以以那个char为分隔符，recursion它的左边和右边 