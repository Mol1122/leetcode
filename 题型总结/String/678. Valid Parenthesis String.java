/* Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 

Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "(*)"
Output: true
Example 3:

Input: s = "(*))"
Output: true         */

class Solution {
    public boolean checkValidString(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] sc = s.toCharArray();
        int leftCount = 0, rightCount = 0;
        int n = sc.length;

        for (int i = 0; i < n; i++) {
            //traverse from left
            if (sc[i] == '(' || sc[i] == '*') {
                leftCount++;
            } else {
                leftCount--;
            }

            //traverse from right
            if (sc[n - 1 - i] == ')' || sc[n - 1 - i] == '*') {
                rightCount++;
            } else {
                rightCount--;
            }
            if (leftCount < 0 || rightCount < 0) {
                return false;
            }
        }
        return true;
    }
}
//time: O(n), space: O(n) could be O(1) if not using sc