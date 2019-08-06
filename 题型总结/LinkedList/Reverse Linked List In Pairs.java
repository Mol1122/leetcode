/**
Reverse pairs of elements in a singly-linked list.

Examples

L = null, after reverse is null
L = 1 -> null, after reverse is 1 -> null
L = 1 -> 2 -> null, after reverse is 2 -> 1 -> null
L = 1 -> 2 -> 3 -> null, after reverse is 2 -> 1 -> 3 -> null */

public class Solution {
  public ListNode reverseInPairs(ListNode head) {
      //iterative
      /* 时间复杂度：O(n) 空间复杂度：O(1) 难点：不一定变换linked list的位置就一定要动pointer */
    /*  if (head == null || head.next == null) {
          return head;
      }
      ListNode curr = head;
      while (curr != null && curr.next != null) {
          int temp = curr.value;
          curr.value = curr.next.value;
          curr.next.value = temp;
          curr = curr.next.next;
      }
      return head;  */
    
      //recursion
      //time: O(n), space: O(n)
      if (head == null || head.next == null) {
          return head;
      }
      ListNode next = head.next;
      head.next = reverseInPairs(head.next.next);
      next.next = head;
      return next;
  }
}

