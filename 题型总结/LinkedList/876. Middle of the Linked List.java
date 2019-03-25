/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    //如果是偶数个，return第二个middle node
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

/* 如果是偶数个，return第一个middle node
  public ListNode middleNode(ListNode head) {
      if (head == null || head.next == null) {
          return head;
      }
      ListNode slow = head, fast = head;
      while (fast.next != null && fast.next.next != null) {
          slow = slow.next;
          fast = fast.next.next;
      }
      return slow;
  } */

/* 时间复杂度：O(n)
** 空间复杂度：O(1) */