/**
Given a singly-linked list, where each node contains an integer value, sort it in ascending order. The merge sort algorithm should be used to solve this problem.

Examples

null, is sorted to null
1 -> null, is sorted to 1 -> null
1 -> 2 -> 3 -> null, is sorted to 1 -> 2 -> 3 -> null
4 -> 2 -> 6 -> -3 -> 5 -> null, is sorted to -3 -> 2 -> 4 -> 5 -> 6 */

public class Solution {
  public ListNode mergeSort(ListNode head) {
      if (head == null || head.next == null) {
          return head;
      }
      ListNode middle = getMiddle(head);
      ListNode middleNext = middle.next;
      middle.next = null;
      ListNode first = mergeSort(head);
      ListNode second = mergeSort(middleNext);
    
      return merge(first, second);
  }
  
  private ListNode merge(ListNode head1, ListNode head2) {
      ListNode dummy = new ListNode(-1);
      ListNode tail = dummy;
    
      while (head1 != null && head2 != null) {
          if (head1.value < head2.value) {
              tail.next = head1;
              head1 = head1.next;
          } else {
              tail.next = head2;
              head2 = head2.next;
          }
          tail = tail.next;
      }
      if (head1 != null) {
          tail.next = head1;
      }
      if (head2 != null) {
          tail.next = head2;
      }
      return dummy.next;
  }
  
  private ListNode getMiddle(ListNode head) {
      ListNode slow = head, fast = head;
    
      while (fast.next != null && fast.next.next != null) {
          slow = slow.next;
          fast = fast.next.next;
      }
      return slow;
  }
}

//time: O(nlogn), space: O(logn)
