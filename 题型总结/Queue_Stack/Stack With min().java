//常规做法
public class Solution {
  Deque<Integer> stack1;
  Deque<Integer> stack2;

  public Solution() {
    stack1 = new ArrayDeque<>();
    stack2 = new ArrayDeque<>();
  }
  
  public int pop() {
    if (stack1.isEmpty()) {
      return -1;
    }
    stack2.pollLast();
    return stack1.pollLast();
  }
  
  public void push(int element) {
    stack1.offerLast(element);
    if (stack2.isEmpty()) {
      stack2.offerLast(element);
    } else {
      stack2.offerLast(Math.min(stack2.peekLast(), element));
    }
  }
  
  public int top() {
    if (stack1.isEmpty()) {
      return -1;
    }
    return stack1.peekLast();
  }
  
  public int min() {
    if (stack1.isEmpty()) {
      return -1;
    }
    return stack2.peekLast();
  }
}
//time: O(n), space: O(1)


// How to optimize the space usage of minStack assuming that there are a lot of duplicates?
public class Solution {
  Deque<Integer> stack;
  Deque<Pair> minStack;

  public Solution() {
    stack = new ArrayDeque<>();
    minStack = new ArrayDeque<>();
  }
  
  public int pop() {
    if (stack.isEmpty()) {
      return -1;
    }
    int size = stack.size();
    int value = stack.pollLast();
    if (minStack.peekLast().e2 == size) {
      minStack.pollLast();
    }
    return value;
  }
  
  public void push(int element) {
    stack.offerLast(element);
    if (minStack.isEmpty()) {
      minStack.offerLast(new Pair(element, stack.size()));
    } else {
      if (element < minStack.peekLast().e1) {
        minStack.offerLast(new Pair(element, stack.size()));
      }
    }
  }
  
  public int top() {
    if (stack.isEmpty()) {
      return -1;
    }
    return stack.peekLast();
  }
  
  public int min() {
    if (stack.isEmpty()) {
      return -1;
    }
    return minStack.peekLast().e1;
  }
}

class Pair {
    int e1; //value
    int e2; //the size of Stack1, when e1=value becomes the min of Stack1 for the 1st time
    
    public Pair(int e1, int e2) {
        this.e1 = e1;
        this.e2 = e2;
    } 
}