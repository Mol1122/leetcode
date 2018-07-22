/**
 * Definition for ListNode
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
     * @param node: a list node in the list
     * @param x: An integer
     * @return: the inserted new list node
     */
    public ListNode insert(ListNode node, int x) {
        if (node == null) {
            ListNode new_node = new ListNode(x);
            new_node.next = new_node;
            return new_node;
        }
        ListNode curr = node;
        ListNode prev = null;
        
        do {
            prev = curr;
            curr = curr.next;
            
            if (x <= curr.val && x >= prev.val) { //已经找到位置
                break;
            }
            if (prev.val > curr.val && (x >= prev.val || x <= curr.val)) { //要么比所有数都大，要么比所有数都小
                break;
            }
            
        } while (curr != node);
        
        ListNode new_node = new ListNode(x);
        new_node.next = curr;
        prev.next = new_node;
        
        return new_node;
    }
}