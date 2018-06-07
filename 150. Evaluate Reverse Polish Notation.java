class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        String ops = "+-*/";
        for (String str : tokens) {
            if (!ops.contains(str)) {
                stack.push(Integer.parseInt(str));
                continue;
            }
            int a = stack.pop();
            int b = stack.pop();
            if (str.equals("+")) {
                stack.push(a + b);
            } else if (str.equals("-")) {
                stack.push(b - a);
            } else if (str.equals("*")) {
                stack.push(a * b);
            } else if (str.equals("/")) {
                stack.push(b / a);
            }
        }
        return stack.peek();
    }
}

/* 算法：利用stack去暂时保存值 */