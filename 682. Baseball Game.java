class Solution {
    public int calPoints(String[] ops) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < ops.length; i++) {
            if (ops[i].equals("+")) {
                int temp1 = stack.pop();
                int temp2 = stack.pop();
                int add_two = temp1 + temp2;
                sum += add_two;
                stack.push(temp2);
                stack.push(temp1);
                stack.push(add_two);
            } else if (ops[i].equals("D")) {
                int temp1 = stack.pop();
                int multi = 2 * temp1;
                sum += multi;
                stack.push(temp1);
                stack.push(multi);
            } else if (ops[i].equals("C")) {
                int cancel = stack.pop();
                sum -= cancel;
            } else {
                int temp = Integer.parseInt(ops[i]);
                sum += temp;
                stack.push(temp);
            }
        }
        return sum;
    }
}

/* 算法：很经典的用stack保存之前的值 */