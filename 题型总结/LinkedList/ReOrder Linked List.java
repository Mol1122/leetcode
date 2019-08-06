/**
Reorder the given singly-linked list N1 -> N2 -> N3 -> N4 -> … -> Nn -> null to be N1- > Nn -> N2 -> Nn-1 -> N3 -> Nn-2 -> … -> null

Examples

L = null, is reordered to null
L = 1 -> null, is reordered to 1 -> null
L = 1 -> 2 -> 3 -> 4 -> null, is reordered to 1 -> 4 -> 2 -> 3 -> null
L = 1 -> 2 -> 3 -> null, is reordred to 1 -> 3 -> 2 -> null */   

public class Solution {
  public ListNode reorder(ListNode head) {
      if (head == null || head.next == null) {
            return head;
        }
        ListNode middle = getMiddle(head);
        ListNode tail = reverse(middle.next);
        middle.next = null; //important
        
        return merge(head, tail);
  }
  
  private ListNode merge(ListNode head1, ListNode head2) {
  /*      ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        int index = 0;
        
        while (head1 != null || head2 != null) {
            if (index % 2 == 0) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            index++;
            tail = tail.next;
        }
        if (head1 != null) {
            tail.next = head1;
        }
        if (head2 != null) {
            tail.next = head2;
        }
        return dummy.next;  */
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        
        while (head1 != null && head2 != null) {
            tail.next = head1;
            head1 = head1.next;
            tail = tail.next;
            tail.next = head2;
            head2 = head2.next;
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
    
    private ListNode reverse(ListNode head) {
        ListNode next = null, prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
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
//time: O(n), space: O(1)
