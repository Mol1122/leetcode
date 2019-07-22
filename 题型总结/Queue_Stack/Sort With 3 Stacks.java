/* Given one stack with integers, sort it with two additional stacks (total 3 stacks). 

After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted in ascending order.

Assumptions:

The given stack is not null.
 */

public class Solution {
  public void sort(LinkedList<Integer> s1) { //intput
    LinkedList<Integer> s2 = new LinkedList<Integer>(); //buffer
    LinkedList<Integer> s3 = new LinkedList<Integer>(); //output
    // Write your solution here.
    if (s1.size() <= 1) {
        return;
    }
   int globalMin = Integer.MAX_VALUE;
   int count = 0;
   int length = s1.size();
    
    for (int i = 0; i < length; i++) {
        globalMin = Integer.MAX_VALUE;
        count = 0;
        while (s1.size() > 0) {
            int val = s1.pollLast();
            s2.offerLast(val);
            if (val < globalMin) {
              globalMin = val;
              count = 1;            
            } else if (val == globalMin) {
              count++;
            }
        }
        while (count > 0) {
          s3.offerLast(globalMin);
          count--;
        }
        while (s2.size() > 0) {
            if (s2.peekLast() != globalMin) {
                s1.offerLast(s2.pollLast());
            } else {
                s2.pollLast();
            }
        }
    } 
    s1.clear();
    for (int i = 0; i < s3.size(); i++) {
      s1.add(s3.get(i));
    }
  }
}
//time: O(n^2), space: O(n)