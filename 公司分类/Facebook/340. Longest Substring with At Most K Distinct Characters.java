/* Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2. */

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }   
        Map<Character, Integer> map = new HashMap<>();
        int j = 0, max = 0;;
        
        for (int i = 0; i < s.length(); i++) {
            while (j < s.length() && (map.size() < k || map.size() == k && map.containsKey(s.charAt(j)))) {
                map.putIfAbsent(s.charAt(j), 0);
                map.put(s.charAt(j), map.get(s.charAt(j)) + 1);
                j++;
            }
            max = Math.max(max, j - i);
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                if (map.get(s.charAt(i)) == 0) {
                    map.remove(s.charAt(i));
                }
            }
        }
        return max;
    }
}
//time: O(n), space: O(n)