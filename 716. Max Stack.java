class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> maxStack;

    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack<Integer>();
        maxStack = new Stack<Integer>();
    }
    
    public void push(int x) {
        pushHelper(x);
    }
    
    private void pushHelper(int x) {
        int temp = maxStack.isEmpty() ? Integer.MIN_VALUE : maxStack.peek();
        if (x > temp) {
            temp = x;
        }
        stack.push(x);
        maxStack.push(temp);
    }
    
    
    public int pop() {
        maxStack.pop();
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return maxStack.peek();
    }
    
    public int popMax() {
        Stack<Integer> temp = new Stack<>();
        int max = maxStack.peek();
        while (stack.peek() != max) {
            temp.push(stack.pop());
            maxStack.pop();
        }
        stack.pop();
        maxStack.pop();
        while (!temp.isEmpty()) {
            int x = temp.pop();
            pushHelper(x);
        }
        return max;
    }
}


/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */

/* 算法：用另外一个stack去保存max
** 难点：push需要另外用pushHelper,不能直接通过stakc.push()实现*/