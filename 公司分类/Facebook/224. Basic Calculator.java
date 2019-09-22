/* Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, 
non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2
Example 2:

Input: " 2-1 + 2 "
Output: 3
Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23 */

class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int result = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int number = 0;
        int sign = 1;
        
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                number = number * 10 + c - '0';
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                stack.offerLast(result);
                stack.offer(sign);
                result = 0;
                number = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * number;
                result *= stack.pollLast();
                result += stack.pollLast();
                number = 0;
                sign = 1;
            }
        }
        if (number != 0) {
            result += sign * number;
        }
        return result;
    }
}
//time: O(n), space: O(n)