/**
Reverse a singly-linked list.

Examples

L = null, return null
L = 1 -> null, return 1 -> null
L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null
 */

class Solution {
    public ListNode reverseList(ListNode head) {
        //Iterative way
        //时间复杂度：O(n), 空间复杂度：O(1)
//         ListNode next = null, prev = null;
//         ListNode curr = head;
        
//         while (curr != null) {
//             next = curr.next;
//             curr.next = prev;
//             prev = curr;
//             curr = next;
//         }
//         return prev;

        //Recursive way
        //时间复杂度：O(n), 空间复杂度：O(n)
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }
}
