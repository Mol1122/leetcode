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
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists(List<ListNode> lists) {  
        //利用归并的方法。思想，时间复杂度：O(Nlogk)
        if (lists == null || lists.size() == 0) {
            return null;
        }
        return mergeHelper(lists, 0, lists.size() - 1);
        
        //merge two by two, 时间复杂度：O(Nlogk)
        // if (lists == null || lists.size() == 0) {
        //     return null;
        // }
        // while (lists.size() > 1) {
        //     List<ListNode> new_list = new ArrayList<>();
        //     for (int i = 0; i + 1 < lists.size(); i += 2) {
        //         ListNode node = merge(lists.get(i), lists.get(i + 1));
        //         new_list.add(node);
        //     }
        //     if (lists.size() % 2 == 1) {
        //         new_list.add(lists.get(lists.size() - 1));
        //     }
        //     lists = new_list;
        // }
        // return lists.get(0);
        
        //heap, 时间复杂度：O(Nlogk)
        // if (lists == null || lists.size() == 0) {
        //     return null;
        // }
        // Queue<ListNode> heap = new PriorityQueue<>(lists.size(), new Comparator<ListNode>() {
        //     public int compare(ListNode n1, ListNode n2) {
        //         return n1.val - n2.val;
        //     }
        // });
        
        // for (int i = 0; i < lists.size(); i++) {
        //     if (lists.get(i) != null) {
        //         heap.add(lists.get(i));
        //     }
        // }
        // ListNode dummy = new ListNode(-1);
        // ListNode tail = dummy;
        // while (!heap.isEmpty()) {
        //     ListNode node = heap.poll();
        //     tail.next = node;
        //     tail = node;
        //     if (node.next != null) {
        //         node = node.next;
        //         heap.add(node);
        //     }
        // }
        // return dummy.next;
    }
    
    private ListNode mergeHelper(List<ListNode> lists, int start, int end) {
        if (start == end) {
            return lists.get(start);
        }
        int mid = start + (end - start) / 2;
        ListNode left = mergeHelper(lists, start, mid);
        ListNode right = mergeHelper(lists, mid + 1, end);
        return merge1(left, right);
    }
    
    private ListNode merge1(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (a != null && b != null) {
            if (a.val < b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        if (a != null) {
            tail.next = a;
        } else {
            tail.next = b;
        }
        return dummy.next;
    }
    
    private ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (a != null && b != null) {
            if (a.val < b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        if (a != null) {
            tail.next = a;
        } else {
            tail.next = b;
        }
        return dummy.next;
    }
}
