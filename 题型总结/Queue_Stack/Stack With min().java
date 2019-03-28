//常规做法
public class Solution {
  Stack<Integer> stack;
  Stack<Integer> minStack;
  
  public Solution() {
    stack = new Stack<>();
    minStack = new Stack<>();
  }
  
  public int pop() {
    if (stack.isEmpty()) {
        return -1;
    }
    minStack.pop();
    return stack.pop();
  }
  
  public void push(int element) {
    stack.push(element);
    if (minStack.isEmpty()) {
        minStack.push(element);
    } else {
        if (element <= minStack.peek()) {
            minStack.push(element);
        } else {
            minStack.push(minStack.peek());
        }
    }
  }
  
  public int top() {
    if (stack.isEmpty()) {
        return -1;
    }
    return stack.peek();
  }
  
  public int min() {
    if (stack.isEmpty()) {
        return -1;
    }
    return minStack.peek();
  }
}

/* 时间复杂度：O(1), space: O(n) */


// How to optimize the space usage of minStack assuming that there are a lot of duplicates?
public class Solution {
  Stack<Integer> stack;
  Stack<Pair> minStack;
  public Solution() {
    stack = new Stack<>();
    minStack = new Stack<>();
  }
  
  public int pop() {
    if (stack.isEmpty()) {
      return -1;
    }
    int size = stack.size();
    int value = stack.pop();
    if (minStack.peek().e2 == size) {
        minStack.pop();
    }
    return value;
  }
  
  public void push(int x) {
    stack.push(x);
    if (minStack.isEmpty()) {
        minStack.push(new Pair(x, stack.size()));
    } else {
        if (x < minStack.peek().e1) {
            minStack.push(new Pair(x, stack.size()));
        }
    }
  }
  
  public int top() {
    if (stack.isEmpty()) {
        return -1;
    }
    return stack.peek();
  }
  
  public int min() {
    if (stack.isEmpty()) {
       return -1;
    }
    return minStack.peek().e1;
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