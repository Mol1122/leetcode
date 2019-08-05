/**
Insert a new element at a specific index in the given linked list. The index is 0 based, and if the index is out of the list's scope, you do not need to do anything.

Examples:

1 -> 2 -> 3 -> null, insert 4 at index 3, --> 1 -> 2 -> 3 -> 4 -> null

1 -> 2 -> null, insert 4 at index 0, --> 4 -> 1 -> 2 -> null */

public class Solution {
  public ListNode insert(ListNode head, int index, int value) {
      ListNode newNode = new ListNode(value);
      if (index == 0) {
          newNode.next = head;
          return newNode;
      }
      if (head == null) {
          return null;
      }
      ListNode curr = head, prev = null;
    
      while (curr != null && index > 0) {
          prev = curr;
          curr = curr.next;
          index--;
      }
      if (index == 0) {
          newNode.next = curr;
          prev.next = newNode;
      }
      return head;
  }
}
/* 时间复杂度：O(n)
** 空间复杂度：O(1) */
