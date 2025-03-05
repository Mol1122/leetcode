/* A string is good if there are no repeated characters.

Given a string s, return the number of good substrings of length three in s.

Note that if there are multiple occurrences of the same substring, every occurrence should be counted.

A substring is a contiguous sequence of characters in a string.

 
Example 1:

Input: s = "xyzzaz"
Output: 1
Explanation: There are 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz". 
The only good substring of length 3 is "xyz".
Example 2:

Input: s = "aababcabc"
Output: 4
Explanation: There are 7 substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
The good substrings are "abc", "bca", "cab", and "abc" */

class Solution {
    public int countGoodSubstrings(String s) {
        if (s == null || s.length() < 3) {
            return 0;
        }
        int[] cnt = new int[26];
        char[] sc = s.toCharArray();
        int count = 0;

        for (int i = 0; i < sc.length; i++) {
            cnt[sc[i] - 'a']++;
            if (i >= 2) {
                if (isValid(sc, i, cnt)) {
                    count++;
                }
                cnt[sc[i - 2] - 'a']--;
            }
        }
        return count++;
    }

    private boolean isValid(char[] sc, int endIndex, int[] cnt) {
        for (int i = endIndex - 2; i <= endIndex; i++) {
            if (cnt[sc[i] - 'a'] > 1) {
                return false;
            }
        }
        return true;
    }
}
//time: O(n), space: O(n)