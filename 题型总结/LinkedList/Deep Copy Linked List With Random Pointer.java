/** Each of the nodes in the linked list has another pointer pointing to a random node in the list or null. 
Make a deep copy of the original list. */

public class Solution {
  public RandomListNode copy(RandomListNode head) {
      if (head == null) {
          return null;
      }
      Map<RandomListNode, RandomListNode> map = new HashMap<>();
      RandomListNode dummy = new RandomListNode(0);
      RandomListNode tail = dummy;
    
      while (head != null) {
          if (!map.containsKey(head)) {
              map.put(head, new RandomListNode(head.value));
          }
          tail.next = map.get(head);
          
          if (head.random != null) {
              if (!map.containsKey(head.random)) {
                  map.put(head.random, new RandomListNode(head.random.value));
              }
              tail.next.random = map.get(head.random);
          }
          head = head.next;
          tail = tail.next;
      }
      return dummy.next;
  }
}
//time: O(n), space: O(n)
