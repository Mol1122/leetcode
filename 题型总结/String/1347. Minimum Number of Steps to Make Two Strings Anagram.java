/* You are given two strings of the same length s and t. In one step you can choose any character of t and replace it with another character.

Return the minimum number of steps to make t an anagram of s.

An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.

 

Example 1:

Input: s = "bab", t = "aba"
Output: 1
Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
Example 2:

Input: s = "leetcode", t = "practice"
Output: 5
Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
Example 3:

Input: s = "anagram", t = "mangaar"
Output: 0
Explanation: "anagram" and "mangaar" are anagrams. 
 */

 class Solution {
    public int minSteps(String s, String t) {
        int[] det = new int[26];

        for (char c : s.toCharArray()) {
            det[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            det[c - 'a']--;
        }
        int absSum = 0;
        for (int i = 0; i < 26; i++) {
            absSum += Math.abs(det[i]);
        }
        return absSum / 2;
    }
}
//难点：把一个char变成另一个char的时候，原来的char count -1, 新的char count + 1. 所以算出来不同的char count后要除以2
//time: O(n), space: O(1)