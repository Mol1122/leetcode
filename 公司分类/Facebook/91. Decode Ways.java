/* A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6). */

class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] sc = s.toCharArray();
        int[] f = new int[sc.length + 1];
        f[0] = 1;
        
        for (int i = 1; i <= sc.length; i++) {
            f[i] = 0;
            if (sc[i - 1] != '0') {
                f[i] += f[i - 1];
            }
            if (i >= 2) {
                int val = (sc[i - 2] - '0') * 10 + (sc[i - 1] - '0');
                if (val >= 10 && val <= 26) {
                    f[i] += f[i - 2];
                }
            }
        }
        return f[sc.length];
    }
}
//time: O(n), space: O(n)