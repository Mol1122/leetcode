/**
Given a linked list and an sorted array of integers as the indices in the list. Delete all the nodes at the indices in the original list.

Examples

1 -> 2 -> 3 -> 4 -> NULL, indices = {0, 3, 5}, after deletion the list is 2 -> 3 -> NULL.

Assumptions

the given indices array is not null and it is guaranteed to contain non-negative integers sorted in ascending order. */

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
