/* Given the head of a linked list, find all the values that appear more than once in the list and delete the 
nodes that have any of those values.

Return the linked list after the deletions.

Example 1:


Input: head = [1,2,3,2]
Output: [1,3]
Explanation: 2 appears twice in the linked list, so all 2's should be deleted. After deleting all 2's, we are left with [1,3]. */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        if (head == null) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        while (head != null) {
            map.putIfAbsent(head.val, 0);
            map.put(head.val, map.get(head.val) + 1);
            head = head.next;
        }
        head = dummy;
        while (head.next != null) {
            if (map.get(head.next.val) != 1) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }
}
//time: O(n), space: O(n)