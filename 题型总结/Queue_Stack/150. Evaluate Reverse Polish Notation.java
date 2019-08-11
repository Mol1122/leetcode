/* Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9 */

class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        String ops = "+-*/";
        
        for (String s : tokens) {
            if (ops.contains(s)) {
                int num2 = stack.pollLast();
                int num1 = stack.pollLast();
                if (s.equals("+")) {
                    stack.offerLast(num1 + num2);
                } else if (s.equals("-")) {
                    stack.offerLast(num1 - num2);
                } else if (s.equals("*")) {
                    stack.offerLast(num1 * num2);
                } else {
                    stack.offerLast(num1 / num2);
                }
            } else {
                stack.offerLast(Integer.parseInt(s));
            }
        }
        return stack.pollLast();
    }
}
//time: O(n), space: O(n)