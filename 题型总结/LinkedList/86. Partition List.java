/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode smallHead = new ListNode(-1), smallTail = smallHead;
        ListNode largeHead = new ListNode(-1), largeTail = largeHead;
        
        while (head != null) {
            if (head.val < x) {
                smallTail.next = head;
                smallTail = smallTail.next;
        
            } else {
                largeTail.next = head;
                largeTail = largeTail.next;
            }
            head = head.next;
        }
        smallTail.next = largeHead.next;
        largeTail.next = null;
        return smallHead.next;
    }
}