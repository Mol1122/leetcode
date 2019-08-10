/* Implement a stack containing integers using queue(s). The stack should provide push(x), pop(), top() and isEmpty() operations.

In java: if the stack is empty, then top() and pop() will return null.

In Python: if the stack is empty, then top() and pop() will return None.

In C++:  if the stack is empty, then top() and pop() will return nullptr. */

class Solution {
    Queue<Integer> queue1;
    Queue<Integer> queue2;
    
    public Solution() {
       queue1 = new LinkedList<>();
       queue2 = new LinkedList<>();
    }

    public void push(int x) {
        queue1.offer(x);
    }

    public Integer pop() {
        if (isEmpty()) {
          return null;
        }
        moveItems();
        Integer val = queue1.poll();
        swap();
        return val;
    }

    private void moveItems() {
      while (queue1.size() != 1) {
        queue2.offer(queue1.poll());
      }
    }

    private void swap() {
      Queue<Integer> temp = queue1;
      queue1 = queue2;
      queue2 = temp;
    }

    public Integer top() {
        if (isEmpty()) {
          return null;
        }
        moveItems();
        Integer val = queue1.poll();
        swap();
        queue1.offer(val);
        return val;
    }

    public boolean isEmpty() {
       return queue1.isEmpty();
    }
}