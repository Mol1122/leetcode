/* Insert a value in a sorted linked list.

Examples

L = null, insert 1, return 1 -> null
L = 1 -> 3 -> 5 -> null, insert 2, return 1 -> 2 -> 3 -> 5 -> null
L = 1 -> 3 -> 5 -> null, insert 3, return 1 -> 3 -> 3 -> 5 -> null
L = 2 -> 3 -> null, insert 1, return 1 -> 2 -> 3 -> null */

public class Solution {
  public ListNode insert(ListNode head, int target) {
      //这个方法更好
      ListNode newNode = new ListNode(target);
      if (head == null) {
          return newNode;
      }
      ListNode curr = head;
      if (target < head.value) {
          newNode.next = head;
          head = newNode;
          return head;
      } else {
          while (curr.next != null && curr.next.value < target) {
              curr = curr.next;
          }
          newNode.next = curr.next;
          curr.next = newNode;
      }
      return head;
    
   /*   ListNode newNode = new ListNode(target);
      if (head == null) {
          return newNode;
      }
      ListNode curr = head, prev = null;
      while (curr != null && curr.value < target) {
          prev = curr;
          curr = curr.next;
      }
      if (prev == null) {
          newNode.next = head;
          head = newNode;
          return head;
      } else {
          prev.next = newNode;
          newNode.next = curr;
          return head;
      }  */
  }
}
