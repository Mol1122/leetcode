/* Given a string s and an integer k, return the total number of substrings of s where at least one character appears at least k times.


Example 1:

Input: s = "abacb", k = 2

Output: 4

Explanation:

The valid substrings are:

"aba" (character 'a' appears 2 times).
"abac" (character 'a' appears 2 times).
"abacb" (character 'a' appears 2 times).
"bacb" (character 'b' appears 2 times).
Example 2:

Input: s = "abcde", k = 1

Output: 15

Explanation:

All substrings are valid because every character appears at least once. */

class Solution {
    public long numberOfSubstrings(String s, int k) {
        if (s == null || s.length() < k) {
            return 0;
        }
        char[] sc = s.toCharArray();
        int[] cnt = new int[26];
        long count = 0;
        int j = 0;

        for (int i = 0; i < sc.length; i++) {
            while (j < sc.length) {
                cnt[sc[j] - 'a']++;
                if (cnt[sc[j] - 'a'] >= k) {
                    count += sc.length - j;
                    cnt[sc[j] - 'a']--;
                    break;
                } else {
                    j++;
                }
            }
     
            cnt[sc[i] - 'a']--;
        }
        return count;
    }
}
//time: O(n), O(n)