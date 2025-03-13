/* Description
Given a linked list, determine if it has a cycle in it. 

Example 1:

Input:

linked list = 21->10->4->5
tail connects to node index 1(value 10).
Output:

true
Explanation:

The linked list has rings.

Example 2:

Input:

linked list = 21->10->4->5->null
Output:

false                            */

public class Solution {
    /**
     * @param head: The first node of linked list.
     * @return: True if it has a cycle, or false
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head, fast = head.next;

        while (slow != fast) {
            if (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            } else {
                return false;
            }
        }
        return true;
    }
}
//time: O(n), space: O(1)