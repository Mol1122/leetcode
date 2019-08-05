/**
Given a linked list, check whether it is a palindrome.

Examples:

Input:   1 -> 2 -> 3 -> 2 -> 1 -> null

output: true.

Input:   1 -> 2 -> 3 -> null  

output: false.

Requirements:

Space complexity must be O(1) */

public class Solution {
  public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }
    ListNode middle = getMiddle(head);
    ListNode tail = reverseList(middle.next);
    
    ListNode l1 = head, l2 = tail;
    while (l1 != null && l2 != null && l1.value == l2.value) {
      l1 = l1.next;
      l2 = l2.next;
    }
    return l2 == null;
  }

  private ListNode reverseList(ListNode head) {
    ListNode prev = null, next = null;

    while (head != null) {
      next = head.next;
      head.next = prev;
      prev = head;
      head = next;
    }
    return prev;
  }

  private ListNode getMiddle(ListNode head) {
    ListNode slow = head, fast = head.next;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }
}
//time: O(n), space: O(1)