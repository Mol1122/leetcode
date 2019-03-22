/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
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
              minheap.add(lists.get(i));
          }
      }
      ListNode dummy = new ListNode(-1);
      ListNode tail = dummy;
      while (!minheap.isEmpty()) {
          ListNode node = minheap.poll();
          tail.next = node;
          tail = tail.next;
          if (node.next != null) {
              node = node.next;
              minheap.offer(node);
          }
      }
      return dummy.next;
  }
}
