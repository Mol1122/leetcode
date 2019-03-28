public class Solution {
  Stack<Integer> stack1;
  Stack<Integer> stack2; //store the value
  public Solution() {
    stack1 = new Stack<>();
    stack2 = new Stack<>();
  }
  
  public Integer poll() {
    if (size() == 0) {
        return null;
    }
    if (stack1.isEmpty()) {
        stack2Tostack1();
    }
    return stack1.pop();
  }
  
  private void stack2Tostack1() {
      while (!stack2.isEmpty()) {
          stack1.push(stack2.pop());
      }
  }
  
  public void offer(int element) {
    stack2.push(element);
  }
  
  public Integer peek() {
    if (stack1.isEmpty()) {
        stack2Tostack1();
    }
    if (stack1.isEmpty()) {
        return null;
    }
    return stack1.peek();
  }
  
  public int size() {
    return stack1.size() + stack2.size();
  }
  
  public boolean isEmpty() {
    return stack2.isEmpty() && stack1.isEmpty();
  }
}
