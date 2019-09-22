/* Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5 */

class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char sign = '+';
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } 
            if (c != ' ' && !Character.isDigit(c) || i == s.length() - 1) { //这个if statement必须跟上一个if平行，不然没有办法把最后一个数字放进去
                if (sign == '+') {
                    stack.offerLast(num);
                } else if (sign == '-') {
                    stack.offerLast(-num);
                } else if (sign == '*') {
                    stack.offerLast(stack.pollLast() * num);
                } else {
                    stack.offerLast(stack.pollLast() / num);
                }
                sign = c;
                num = 0;
            }
            
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pollLast();
        }
        return result;
    }
}
//time: O(n), space: On()