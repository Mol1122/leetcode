/* Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb" */

class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        int startIndex = 0, length = 0;
        char[] sc = s.toCharArray();
        
        for (int i = 0; i < sc.length; i++) {
            isPalindrome[i][i] = true;
            startIndex = i;
            length = 1;
        }
        for (int i = 0; i < sc.length - 1; i++) {
            isPalindrome[i][i + 1] = sc[i] == sc[i + 1];
            if (isPalindrome[i][i + 1]) {
                startIndex = i;
                length = 2;
            }
        }
        for (int i = sc.length - 3; i >= 0; i--) {
            for (int j = i + 2; j < sc.length; j++) {
                isPalindrome[i][j] = isPalindrome[i + 1][j - 1] && sc[i] == sc[j];
                if (isPalindrome[i][j]) {
                    if (j - i + 1 > length) {
                        startIndex = i;
                        length = j - i + 1;
                    }
                }
            }
        }
        return s.substring(startIndex, startIndex + length);
    }
}
//算法：区间型动态规划
//time: O(n^2), space: O(n^2)