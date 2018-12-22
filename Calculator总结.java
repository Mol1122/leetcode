//224. Basic Calculator 
/* (  ), the plus + or minus sign -, non-negative integers and empty spaces  */
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int sign = 1;
        int number = 0;
        
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                number = number * 10 + c - '0';
            } else if (c == '+') {
                result += sign * number;
                sign = 1;
                number = 0;
            } else if (c == '-') {
                result += sign * (number);
                sign = -1;
                number = 0;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
                number = 0;
            } else if ( c == ')') {
                result += sign * number;
                result *= stack.pop();
                result += stack.pop();
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

//227. Basic Calculator II
/* contains only non-negative integers, +, -, *, / operators and empty spaces */
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            //碰到下一个operator,需要处理之前的数；如果是最后一个数也要处理
            if (c != ' ' && !Character.isDigit(c) || i + 1 == s.length()) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                sign = c;
                num = 0;
            }
        }
        
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}

/* 算法：不希望用result去记录sum值是因为*“/”会牵扯到上一个数，所以直接用stack保存暂时的值就好 */