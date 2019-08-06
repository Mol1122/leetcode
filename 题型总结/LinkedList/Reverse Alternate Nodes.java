/**
Given a linked list, reverse alternate nodes and append at the end.

Examples:

Input List:    1 -> 2 -> 3 -> 4 -> 5 -> 6

Output List: 1 -> 3 -> 5 -> 6 -> 4 -> 2

Input List:    1 -> 2 -> 3 -> 4 -> 5

Output List: 1 -> 3 -> 5 -> 4 -> 2 */

public class Solution {
  public ListNode reverseAlternate(ListNode head) {
      if (head == null || head.next == null) {
          return head;
      }
      ListNode firstDummy = new ListNode(-1), firstTail = firstDummy;
      ListNode secondDummy = new ListNode(-1), secondTail = secondDummy;
      boolean flag = true;
    
      while (head != null) {
          if (flag) {
              firstTail.next = head;
              firstTail = firstTail.next;
          } else {
              secondTail.next = head;
              secondTail = secondTail.next;
          }
          flag = !flag;
          head = head.next;
      }
      secondTail.next = null;
      secondDummy.next = reverse(secondDummy.next);
      firstTail.next = secondDummy.next;
      return firstDummy.next;
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

/* 时间复杂度：O(n)
** 空间复杂度：O(1) */
