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

//772. Basic Calculator III
/* contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . */
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int sum = 0;
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                sum = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                nums.push(sum);
                sum = 0;
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (ops.peek() != '(') {
                    int val = operation(ops.pop(), nums.pop(), nums.pop());
                    nums.push(val);
                }
                ops.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                //判断当前优先级是否高于前面的
                while (!ops.isEmpty() && precedence(c, ops.peek())) { //检查优先级，前面的优先级如果大于当前，那么先计算前面
                    nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
                }
                ops.push(c);
            }  
        }
        
        while (!ops.isEmpty()) {
                nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
            }
        return nums.pop();
    }
    
    //如果后面优先级高，return 
    private boolean precedence(Character ops1, Character ops2) {
        if (ops2 == '(' || ops2 == ')') {
            return false;
        }
        if ((ops1 == '*' || ops1 == '/') && (ops2 == '+' || ops2 == '-')) {
            return false;
        }
        return true;
    }
    
    private int operation(Character c, int b, int a) {
        if (c == '+') {
            return a + b;
        } else if (c == '-') {
            return a - b;
        } else if (c == '*') {
            return a * b;
        } else if (c == '/') {
            return a / b;
        }
        return -1;
    }
}