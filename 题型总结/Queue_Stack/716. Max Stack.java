/* Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty. 

You must come up with a solution that supports O(1) for each top call and O(logn) for each other call.


*/


//Method 1 TLE
class MaxStack {
    Stack<Integer> stack;
    Stack<Integer> maxStack;

    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (maxStack.isEmpty()) {
            maxStack.push(x);
        } else {
            maxStack.push(Math.max(x, maxStack.peek()));
        }
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
            maxStack.pop();
            temp.push(stack.pop());
        }
        stack.pop();
        maxStack.pop();
        while (!temp.isEmpty()) {
            push(temp.pop());
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

//Method 2
class MaxStack {
    TreeSet<Pair> stack; //sorted based on index
    TreeSet<Pair> maxStack; //sorted based on number value
    int index;

    public MaxStack() {
        stack = new TreeSet<>(new Comparator<Pair>(){
            public int compare(Pair a, Pair b) {
                return a.index == b.index ? a.num - b.num : a.index - b.index;
            }
        });
        maxStack = new TreeSet<>(new Comparator<Pair>(){
            public int compare(Pair a, Pair b) {
                return a.num == b.num ? a.index - b.index : a.num - b.num;
            }
        });

        index = 0;
    }
    
    public void push(int x) { //O(logn)
        Pair p = new Pair(x, index);
        stack.add(p);
        maxStack.add(p);
        index++;
    }
    
    public int pop() { //O(logn)
        Pair p = stack.pollLast();
        maxStack.remove(p);
        return p.num;
    }
    
    public int top() { //O(1)
        return stack.last().num;
    }
    
    public int peekMax() { //O(logn)
        return maxStack.last().num;
    }
    
    public int popMax() { //O(logn)
        Pair maxPair = maxStack.pollLast();
        stack.remove(maxPair);
        return maxPair.num;
    }
}

class Pair {
    int num, index;

    public Pair(int num, int index) {
        this.num = num;
        this.index = index;
    }
}