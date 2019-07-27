/* Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Assumption

Valid operators are +, -, *, /.
Each operand may be an integer or another expression.
Examples

["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6 */

class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        String ops = "+-*/";
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (String s : tokens) {
            if (!ops.contains(s)) {
                stack.offerLast(Integer.parseInt(s));
                continue;
            }
            int num2 = stack.pollLast();
            int num1 = stack.pollLast();
            if (s.equals("+")) {
                stack.offerLast(num1 + num2);
            } else if (s.equals("-")) {
                stack.offerLast(num1 - num2);
            } else if (s.equals("*")) {
                stack.offerLast(num1 * num2);
            } else if (s.equals("/")) {
                stack.offerLast(num1 / num2);
            }
        }
        return stack.pollLast();
    }
}
//time: O(n), space: O(n)