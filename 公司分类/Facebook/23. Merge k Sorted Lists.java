/**
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6 */

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int k = lists.length;
        Queue<ListNode> minheap = new PriorityQueue<>(k, new Comparator<ListNode>(){
            public int compare(ListNode a, ListNode b) {
                return a.val - b.val;
            }
        });
        for (ListNode head : lists) {
            if (head != null) {
                minheap.offer(head);
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        
        while (!minheap.isEmpty()) {
            ListNode node = minheap.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {
                minheap.offer(node.next);
            }
        }
        return dummy.next;
    }
}
//time: O(nlogk), space: O(k)