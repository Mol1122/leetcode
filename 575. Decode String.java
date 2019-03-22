public class Solution {
    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    //decode string
    public String expressionExpand(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Stack<Object> stack = new Stack<>();
        int number = 0;
        
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                number = number  * 10 + c - '0';
            } else if (c == '[') {
                stack.push(number);
                number = 0;
            } else if (c == ']') {
                String str = popStack(stack);
                int count = (Integer)stack.pop();
                while (count > 0) {
                    stack.push(str);
                    count--;
                }
            } else {
                stack.push(String.valueOf(c));
            }
        }
        return popStack(stack);
    }
    
    private String popStack(Stack<Object> stack) {
        Stack<String> temp = new Stack<>();
        while (!stack.isEmpty() && stack.peek() instanceof String) {
            temp.add((String)stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        while (!temp.isEmpty()) {
            sb.append(temp.pop());
        }
        return sb.toString();
    }
}