/* Given two strings s and t, determine if they are both one edit distance apart.

Note: 

There are 3 possiblities to satisify one edit distance apart:

Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t
Example 1:

Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
Example 2:

Input: s = "cab", t = "ad"
Output: false
Explanation: We cannot get t from s by only one step.
Example 3:

Input: s = "1203", t = "1213"
Output: true
Explanation: We can replace '0' with '1' to get t. */

class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() > t.length()) {
            return isOneEditDistance(t, s);
        }
        int diff = t.length() - s.length();
        if (diff > 1) {
            return false;
        }
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        
        int count = 0;
        if (diff == 0) {
            for (int i = 0; i < sc.length; i++) {
                if (sc[i] != tc[i]) {
                    count++;
                }
            }    
            return count == 1;
        }
        for (int i = 0; i < sc.length; i++) {
            if (sc[i] != tc[i]) {
                return s.substring(i).equals(t.substring(i + 1));
            }
        }
        return true;
    }
}
//time: O(n + m), space: O(n + m)