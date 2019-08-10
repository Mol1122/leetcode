/* Given an array that is initially stored in one stack, sort it with one additional stacks (total 2 stacks).

After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted in ascending order.

Assumptions:

The given stack is not null.
There can be duplicated numbers in the give stack.
Requirements:

No additional memory, time complexity = O(n ^ 2). */

public class Solution {
  public void sort(LinkedList<Integer> s1) { //input
    LinkedList<Integer> s2 = new LinkedList<Integer>(); //output + buffer
    
    if (s1.size() <= 1) {
      return;
    }
    int globalMin = Integer.MAX_VALUE;
    int count = 0;
    int length = s1.size();

    for (int i = 0; i < length; i++) {
      globalMin = Integer.MAX_VALUE;
      count = 0;

      while (!s1.isEmpty()) {
        int val = s1.pollLast();
        s2.offerLast(val);
        if (val < globalMin) {
          globalMin = val;
          count = 1;
        } else if (val == globalMin) {
          count++;
        }
      }
      while (!s2.isEmpty() && s2.peekLast() >= globalMin) {
        if (s2.peekLast() == globalMin) {
          s2.pollLast();
        } else {
          s1.offerLast(s2.pollLast());
        }
      }
      while (count > 0) {
        s2.offerLast(globalMin);
        count--;
      }
    }
    s1.clear();
    for (int i = 0; i < s2.size(); i++) {
      s1.add(s2.get(i));
    }
  }
}
//time: O(n^2), space: O(1)
