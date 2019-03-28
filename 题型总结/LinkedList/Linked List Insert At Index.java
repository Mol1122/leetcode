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
