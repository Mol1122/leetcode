/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class Solution {
  public ListNode selectionSort(ListNode head) {
      ListNode dummy = new ListNode(-1);
      ListNode tail = dummy;
      
      while (head != null) {
          ListNode min = head, prev = null;
          ListNode curr = head;
          while (curr.next != null) {
              if (curr.next.value < min.value) {
                  prev = curr;
                  min = curr.next;
              }
              curr = curr.next;
          }
          if (prev == null) {
              head = head.next;
          } else {
              prev.next = min.next;
          }
          min.next = null;
          tail.next = min;
          tail = tail.next;
      }
      return dummy.next;
  }
}

/* 时间复杂度：O(n^2)
** 空间复杂度：O(1) */
