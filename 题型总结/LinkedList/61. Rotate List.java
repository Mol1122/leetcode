/**
Given a list, rotate the list to the right by k places, where k is non-negative.

Input: 1->2->3->4->5->NULL, k = 2

Output: 4->5->1->2->3->NULL

Input: 1->2->3->4->5->NULL, k = 12

Output: 4->5->1->2->3->NULL */

//不容易想
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        
        int length = getLength(head);
        k = k % length;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        ListNode tail = dummy;
        for (int i = 0; i < k; i++) {
            head = head.next;
        }
        
        while (head.next != null) {
            tail = tail.next;
            head = head.next;
        }
        
        head.next = dummy.next;
        dummy.next = tail.next;
        tail.next = null;
        return dummy.next;
    }
    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length ++;
            head = head.next;
        }
        return length;
    }
}

//比较好想的解法
public class Solution {
  public ListNode rotateKplace(ListNode head, int n) {
      if (head == null) {
          return head;
      }
      int length = getLength(head);
      n = n % length;
      if (n == 0) { //这个不能漏，不然最后当n = list size, fast走完，second = null, second.nullhui chu cuo
          return head;
      }
      ListNode first = head, second = null;
      //assume n <= size of linked list
      ListNode slow = head, fast = head.next;
      while (n > 0) {
          fast = fast.next;
          n--;
      }
      //find the head of second part
      while (fast != null) {
          slow = slow.next;
          fast = fast.next;
      }
      second = slow.next;
      slow.next = null;
      
      //find the tail of second part
      ListNode curr = second;
      while (curr.next != null) {
          curr = curr.next;
      }
      curr.next = first;
      return second;
  }
  
  private int getLength(ListNode head) {
      if (head == null) {
          return 0;
      }
      int count = 0;
      while (head != null) {
          count++;
          head = head.next;
      }
      return count;
  }
}