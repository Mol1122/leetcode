/* Java: Implement a queue by using two stacks. The queue should provide size(), 
isEmpty(), offer(), poll() and peek() operations. When the queue is empty, poll() 
and peek() should return null. 

Assumptions

The elements in the queue are all Integers.
size() should return the number of elements buffered in the queue.
isEmpty() should return true if there is no element buffered in the queue, false otherwise. */

public class Solution {
  Deque<Integer> stack1;
  Deque<Integer> stack2;

  public Solution() {
    stack1 = new ArrayDeque<>();
    stack2 = new ArrayDeque<>();
  }
  
  public Integer poll() {
    if (size() == 0) {
      return null;
    }
    if (stack1.isEmpty()) {
      stack2Tostack1();
    }
    return stack1.pollLast();
  }

  private void stack2Tostack1() {
    while (!stack2.isEmpty()) {
      stack1.offerLast(stack2.pollLast());
    }
  }
  
  public void offer(int element) {
    stack2.offerLast(element);
  }
  
  public Integer peek() {
    if (size() == 0) {
      return null;
    }
    if (stack1.isEmpty()) {
      stack2Tostack1();
    }
    return stack1.peekLast();
  }
  
  public int size() {
    return stack2.size() + stack1.size();
  }
  
  public boolean isEmpty() {
    return stack2.isEmpty() && stack1.isEmpty();
  }
}
