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
  public ListNode deleteNodes(ListNode head, int[] indices) {
      if (head == null || indices == null || indices.length == 0) {
          return head;
      }
      for (int i = 0; i < indices.length; i++) {
          head = deleteOneNode(head, indices[i] - i);
      }
      return head;
  }
  
  private ListNode deleteOneNode(ListNode head, int index) {
      if (head == null || index < 0) {
          return head;
      }
      ListNode curr = head, prev = null;
      while (curr != null && index > 0) {
          prev = curr;
          curr = curr.next;
          index--;
      }
      if (curr == null) { //already reach the end
          return head;
      }
      if (prev == null){ //delete the first node
          return head.next;
      } else {
          prev.next = prev.next.next;
          return head;
      }
  }
}

/* 时间复杂度：O(n*len(array)) 
** 空间复杂度：O(1) */
