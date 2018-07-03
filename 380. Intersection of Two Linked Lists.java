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
    /*
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode node = headA;
        while (node.next != null) {
            node = node.next;
        }
        node.next = headB;
        ListNode result = findCycle(headA);
        result.next = null;
        return result;
    }
    
    private ListNode findCycle(ListNode headA) {
        ListNode slow = headA, fast = headA.next;
        
        while (slow != fast) {
            if (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            } else {
                return null;
            }
        }
        slow = headA;
        fast = fast.next; //不能像模版题cycle start一样从slow.next开始，因为前面已经改过值了
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}

/* 算法：先把list1的尾接到list2的头，然后用找intersection list的方法找
** 时间复杂度：O(n) */