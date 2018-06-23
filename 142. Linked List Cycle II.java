/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        
        while (head != slow.next) { //难点 slow.next
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}

/* 算法：先判断是否有循环，有的话从start of list node开始，和the next of the first meeting node开始，每次各走一步，第一次的相遇点就是环形起点 */