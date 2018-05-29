/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        //add headB to the end of the listA
        ListNode node = headA;
        while (node.next != null) {
            node = node.next;
        }
        node.next = headB;
        ListNode result = listCycleII(headA);
        node.next = null; //because we are not allowed to modify the data structure
        return result;
    }
    
    private ListNode listCycleII(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (slow != fast) {  
            if (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            } else {
                return null;
            }
        } 
        //1, 2, 3, 1, 2, 3; slow = 3
        slow = head;
        fast = fast.next;
        while (fast != null && slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
}
/* 难点：第一个slow, fast traversal是为了找到是否有循环，第二个是为了找起始点 */