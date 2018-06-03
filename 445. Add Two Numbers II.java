/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        return reverse(addList(l1, l2));
    }
    
    private ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }
    
    private ListNode addList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        
        int carry = 0;
        while (l1 != null && l2 != null) {
            head.next = new ListNode((l1.val + l2.val + carry) % 10);
            head = head.next;
            carry = (l1.val + l2.val + carry) / 10;
            
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            head.next = new ListNode((l1.val + carry) % 10);
            head = head.next;
            carry = (l1.val + carry) / 10;
            
            l1 = l1.next;
        }
        while (l2 != null) {
            head.next = new ListNode((l2.val + carry) % 10);
            head = head.next;
            carry = (l2.val + carry) / 10;
            
            l2 = l2.next;
        }
        if (carry != 0) {
            head.next = new ListNode(carry);
            head = head.next;
        }
        return dummy.next;
    }
}

/* 如果不能reverse LinkedList就用stack */