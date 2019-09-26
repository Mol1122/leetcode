/* Given a string containing just the characters '(' and ')', find the length of the 
longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()" */

class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.offerLast(i);
            } else {
                if (stack.isEmpty()) {
                    stack.offerLast(i);
                } else if (s.charAt(stack.peekLast()) == '(') {
                    stack.pollLast();
                    max = Math.max(max, i - (stack.isEmpty() ? -1 : stack.peekLast()));
                } else {
                    stack.offerLast(i);
                }
            }
        }
        return max;
    }
}
//time: O(n), space: O(n)