/* Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).

Implement the MyStack class:

void push(int x) Pushes element x to the top of the stack.
int pop() Removes the element on the top of the stack and returns it.
int top() Returns the element on the top of the stack.
boolean empty() Returns true if the stack is empty, false otherwise.
Notes:

You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.
 

Example 1:

Input
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 2, 2, false]

Explanation
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False
 */

//Method 1
class MyStack {
    Queue<Integer> queue1;
    Queue<Integer> queue2; 

    /** Initialize your data structure here. */
    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue1.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        moveItems();
        int item = queue1.poll();
        swapQueues();
        return item;
    }
    
    /** Get the top element. */
    public int top() {
        moveItems();
        int item = queue1.poll();
        swapQueues();
        queue1.offer(item);
        return item;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty();
    }
    
    //move all items except the last one from queue1 to queue2
    private void moveItems() {
        while (queue1.size() > 1) {
            queue2.offer(queue1.poll());
        }
    }
    
    private void swapQueues() {
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }
}

//Method 2: using deque, only one queue
class MyStack {
    Deque<Integer> deque = new ArrayDeque<>();

    public MyStack() {
        
    }
    
    public void push(int x) {
        deque.offerLast(x);
    }
    
    public int pop() {
        int size = deque.size();
        while (size > 1) {
            deque.offerLast(deque.pollFirst());
            size--;
        }
        return deque.pollFirst();
    }
    
    public int top() {
        int size = deque.size();
        while (size > 1) {
            deque.offerLast(deque.pollFirst());
            size--;
        }
        int val = deque.peekFirst();
        deque.offerLast(deque.pollFirst());
        return val;
    }
    
    public boolean empty() {
        return deque.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */