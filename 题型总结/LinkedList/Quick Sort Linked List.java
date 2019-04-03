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
  public ListNode quickSort(ListNode head) {
      if (head == null || head.next == null) {
          return head;
      }
      ListNode leftDummy = new ListNode(-1), leftTail = leftDummy;
      ListNode middleDummy = new ListNode(-1), middleTail = middleDummy;
      ListNode rightDummy = new ListNode(-1), rightTail = rightDummy;
    
      ListNode mid = getMid(head);
    
      while (head != null) {
          if (head.value < mid.value) {
              leftTail.next = head;
              leftTail = leftTail.next;
          } else if (head.value > mid.value) {
              rightTail.next = head;
              rightTail = rightTail.next;
          } else {
              middleTail.next = head;
              middleTail = middleTail.next;
          }
          head = head.next;
      }
      leftTail.next = null;
      middleTail.next = null;
      rightTail.next = null;
      
      ListNode left = quickSort(leftDummy.next);
      ListNode right = quickSort(rightDummy.next);
    
      return concat(left, middleDummy.next, right);
  }
  
  private ListNode concat(ListNode left, ListNode middle, ListNode right) {
      ListNode dummy = new ListNode(-1);
      ListNode tail = dummy;
      
      tail.next = left;
      tail = getTail(tail);
      tail.next = middle;
      tail = getTail(tail);
      tail.next = right;
    
      return dummy.next;
  }
  
  private ListNode getTail(ListNode head) {
      if (head == null) {
          return null;
      }
      while (head.next != null) {
          head = head.next;
      }
      return head;
  }
  
  private ListNode getMid(ListNode head) {
      ListNode slow = head, fast = head;
      
      while (fast.next != null && fast.next.next != null) {
          slow = slow.next;
          fast = fast.next.next;
      }
      return slow;
  }
}

/* 时间复杂度：average O(nlogn)
** 空间复杂度：O(1) */
