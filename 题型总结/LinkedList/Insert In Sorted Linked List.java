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
