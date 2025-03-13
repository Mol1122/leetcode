/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */


public class Solution {
    /*
     * @param head: The first node of linked list.
     * @return: The node where the cycle begins. if there is no cycle, return null
     */
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
                return null; //难点：没有cycle的情况一定不能忘
            }
        }
        slow = head;
        fast = fast.next;
        while (slow != null && slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}

/* 难点：和intersection of two linked list 的区别在于，这个不一定会有cycle */