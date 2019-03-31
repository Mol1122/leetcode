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
        ListNode slow = head, fast = head.next;
        while (slow != fast) {
            if (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            } else {
                return null;
            }
        }
        //从起点和meeting node的下一个node开始
        slow = head;
        fast = fast.next;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}

/* 算法：先判断是否有循环，有的话从start of list node开始，和the next of the first meeting node开始，每次各走一步，第一次的相遇点就是环形起点 */