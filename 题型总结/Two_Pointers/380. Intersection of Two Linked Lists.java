/**
 * Definition for singly-linked list.
 Write a program to find the node at which the intersection of two singly linked lists begins.

 Example 1:

Input:
    A:          a1 → a2
                       ↘
                         c1 → c2 → c3
                       ↗            
    B:     b1 → b2 → b3
Output: c1
Explanation ：begin to intersect at node c1.
Example 2:

Input:
Intersected at 6
1->2->3->4->5->6->7->8->9->10->11->12->13->null
6->7->8->9->10->11->12->13->null
Output: Intersected at 6
Explanation：begin to intersect at node 6.       */


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
        node.next = null; //because we are not allowed to modify the data structure
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
/* 难点：第一个slow, fast traversal是为了找到是否有循环，第二个是为了找起始点 */
/* 算法：先把list1的尾接到list2的头，然后用找intersection list的方法找
** 时间复杂度：O(n) */