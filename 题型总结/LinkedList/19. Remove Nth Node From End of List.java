/**
Given a linked list, remove the nth node from the end of list and return its head.

Assumptions
If n is not valid, you do not need to do anything to the original list.
Try to do this in one pass.

Examples

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5. */

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0) {
          return head;
      }
      ListNode dummy = new ListNode(-1);
      dummy.next = head;
    
      for (int i = 0; i < n; i++) {
          if (head == null) {
              return dummy.next;
          }
          head = head.next;
      }
      ListNode prev = dummy;
      while (head != null) {
          prev = prev.next;
          head = head.next;
      }
      prev.next = prev.next.next;
      return dummy.next;
    }
}

/* 算法：快慢指针
** 时间复杂度：O(n) 
** 空间复杂度：O(1) */