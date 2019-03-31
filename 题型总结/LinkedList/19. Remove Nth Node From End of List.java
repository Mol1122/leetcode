/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
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