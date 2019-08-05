/**
Delete the node at the given index for the given linked list.

Examples

[1, 2, 3], delete at 1 --> [1, 3]

[1, 2, 3], delete at 4 --> [1, 2, 3]

[1, 2, 3], delete at 0 --> [2, 3] */

public class Solution {
  public ListNode deleteNode(ListNode head, int index) {
      if (head == null || index < 0) {
          return head;
      }
      ListNode curr = head, prev = null;
      while (curr != null && index > 0) {
          prev = curr;
          curr = curr.next;
          index--;
      }
      if (curr == null) {
          return head;
      }
      if (prev == null) {
          return head.next;
      } else {
          prev.next = prev.next.next;
          return head;
      }
  }
}

/* 1 -> 2 -> 3 -> 4 
   p    c
   
   Case1: index >= list size
   Case2: delete 1st element
   Case2: delete middle and last
   时间复杂度：O(n), 空间复杂度：O(1)
*/
