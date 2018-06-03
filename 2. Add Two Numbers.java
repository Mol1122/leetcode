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
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        
        int carry = 0;
        for (ListNode i = l1, j = l2; i != null || j != null; ) {
            int sum = carry;
            sum += (i != null) ? i.val : 0;
            sum += (j != null) ? j.val : 0;
            
            head.next = new ListNode(sum % 10);
            head = head.next;
            carry = sum / 10;
            
            i = (i == null) ? i : i.next;
            j = (j == null) ? j : j.next;
        }
        if (carry != 0) {
            head.next = new ListNode(carry);
        }
        return dummy.next;
    }
}

/* 难点：for loop里面的update */