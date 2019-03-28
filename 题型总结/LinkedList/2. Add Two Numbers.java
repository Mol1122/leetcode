/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    //reverse order
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        
        int carry = 0;
        for (ListNode i = l1, j = l2; i != null || j != null; ) {
            int sum = carry;
            sum += (i != null) ? i.val : 0;
            sum += (j != null) ? j.val : 0;
            
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            carry = sum / 10;
            
            i = (i != null) ? i.next : null;
            j = (j != null) ? j.next : null;
        }
        if (carry != 0) {
            tail.next = new ListNode(carry);
        }
        return dummy.next;
    }
}

/* 时间复杂度：O(n)
** 空间复杂度：O(1) */