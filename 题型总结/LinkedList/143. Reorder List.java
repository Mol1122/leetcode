/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode mid = getMiddle(head);
        ListNode tail = reverse(mid.next);
        mid.next = null;
        
        merge(head, tail);
    }
    
    private void merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(-1);
        int index = 0;
        
        while (head1 != null && head2 != null) {
            if (index % 2 == 0) {
                dummy.next = head1;
                head1 = head1.next;
            } else {
                dummy.next = head2;
                head2 = head2.next;
            }
            dummy = dummy.next;
            index++;
        }
        if (head1 != null) {
            dummy.next = head1;
        }
        if (head2 != null) {
            dummy.next = head2;
        }
    }
    
    private ListNode reverse(ListNode head) {
        ListNode curr = head;
        ListNode next = null, prev = null;
        
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    
    private ListNode getMiddle(ListNode head) {
        ListNode slow = head, fast = head.next;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}