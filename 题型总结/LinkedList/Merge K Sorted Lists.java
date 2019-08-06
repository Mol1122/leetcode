/**
Merge K sorted lists into one big sorted list in ascending order.

Assumptions

ListOfLists is not null, and none of the lists is null. */

public class Solution {
  public ListNode merge(List<ListNode> lists) {
    if (lists == null || lists.size() == 0) {
      return null;
    }
    int k = lists.size();
    Queue<ListNode> minheap = new PriorityQueue<>(k, new Comparator<ListNode>(){
      public int compare(ListNode a, ListNode b) {
        return a.value - b.value;
      }
    });
    for (int i = 0; i < k; i++) {
      if (lists.get(i) != null) {
        minheap.offer(lists.get(i));
      }
    }
    ListNode dummy = new ListNode(-1);
    ListNode tail = dummy;

    while (!minheap.isEmpty()) {
      tail.next = minheap.poll();
      tail = tail.next;

      if (tail.next != null) {
        minheap.offer(tail.next);
      }
    }
    return dummy.next;
  }
}
//time: O(nlogk), space: O(k)