/**
 * class RandomListNode {
 *   public int value;
 *   public RandomListNode next;
 *   public RandomListNode random;
 *   public RandomListNode(int value) {
 *     this.value = value;
 *   }
 * }
 */
public class Solution {
  public RandomListNode copy(RandomListNode head) {
      if (head == null) {
          return null;
      }
      Map<RandomListNode, RandomListNode> map = new HashMap<>();
      RandomListNode dummy = new RandomListNode(-1);
      RandomListNode tail = dummy, newNode = null;
    
      while (head != null) {
          //deep copy the node
          if (map.containsKey(head)) {
              newNode = map.get(head);
          } else {
              newNode = new RandomListNode(head.value);
              map.put(head, newNode);
          }
          tail.next = newNode;
        
          //copyt the random
          if (head.random != null) {
              if (map.containsKey(head.random)) {
                  newNode.random = map.get(head.random);
              } else {
                  newNode.random = new RandomListNode(head.random.value);
                  map.put(head.random, newNode.random);
              }
          }
          tail = tail.next;
          head = head.next;
      }
      return dummy.next;
  }
}
