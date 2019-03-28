/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        
        while (head != null) {
            if (head.val != val) {
                tail.next = head;
                tail = tail.next;
            }
            head = head.next;
        }
        tail.next = null; //注意会漏
        return dummy.next;
    }
}

/* 时间复杂度：O(n)
** 空间复杂度：O(1) */