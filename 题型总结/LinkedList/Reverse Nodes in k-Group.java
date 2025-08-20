/**
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is. You may not alter the values in the nodes, only nodes itself may be changed.

Example

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5 */

public class Solution {
  public ListNode reverseKGroup(ListNode head, int k) {
      if (head == null || head.next == null) {
          return head;
      }
      ListNode dummy = new ListNode(-1);
      ListNode tail = dummy, temp = null, prev = null;
      
      int count = 0;
      while (head != null) {
          temp = head;
          count = 0;

          for (int i = 0; i < k; i++) {
              if (head == null) {
                  break;
              }
              count++;
              prev = head;
              head = head.next;
          }
          prev.next = null;

          if (count == k) {
              tail.next = reverse(temp);
          } else {
              tail.next = temp;
          }
          tail = getTail(tail);
      }
      return dummy.next;
  }
  
  private ListNode getTail(ListNode head) {
      if (head == null) {
          return head;
      }
      while (head.next != null) {
          head = head.next;
      }
      return head;
  }
  
  private ListNode reverse(ListNode head) {
      ListNode curr = head, prev = null, next = null;
    
      while (curr != null) {
          next = curr.next;
          curr.next = prev;
          prev = curr;
          curr = next;
      }
      return prev;
  }
}

/* 时间复杂度：O(n). curr会在n/k个位置停留，reverse是O(k). curr相当于市正着走了一次，O(n), reverse的时候又走了次O(n), O(n) + O(n) = O(n). tail 也是从头走到尾，O(n)
** 空间复杂度：O(1) */
