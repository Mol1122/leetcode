/* A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

 

Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
 */

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