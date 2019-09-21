/* Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false */

class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        s = s.toLowerCase();
        int i = 0, j = s.length() - 1;
        
        while (i < j) {
            while (i < j && !isValid(s.charAt(i))) {
                i++;
            }
            if (i == s.length()) {
                return true;
            }
            while (i < j && !isValid(s.charAt(j))) {
                j--;
            }
            if (j < 0) {
                return true;
            }
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    
    private boolean isValid(char c) {
        return Character.isDigit(c) || Character.isLetter(c);
    }
}
//time: O(n), space: O(1)

/* 算法：从两边开始，skip 空格，然后判断
** 难点：Character.isLetter(c) 
** 时间复杂度：O(n) */