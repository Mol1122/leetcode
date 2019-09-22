/* Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', 
and in any positions ) so that the resulting parentheses string is valid.

Formally, a parentheses string is valid if and only if:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.

 

Example 1:

Input: "())"
Output: 1
Example 2:

Input: "((("
Output: 3
Example 3:

Input: "()"
Output: 0
Example 4:

Input: "()))(("
Output: 4 */

class Solution {
    public int minAddToMakeValid(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0, right = 0;
        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            } else {
                left++;
            }
        }
        return left + right;
    }
}
//算法：想像成一个stack, 如果c == ')', 那么可以跟做括号match, 不然右括号数量加一。如果是做括号，左括号数量也是加一
//time: O(n), space: O(1)