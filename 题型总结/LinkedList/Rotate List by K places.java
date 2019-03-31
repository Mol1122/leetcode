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
  public ListNode rotateKplace(ListNode head, int n) {
      if (head == null) {
          return head;
      }
      int length = getLength(head);
      n = n % length;
      if (n == 0) { //这个不能漏，不然最后当n = list size, fast走完，second = null, second.nullhui chu cuo
          return head;
      }
      ListNode first = head, second = null;
      //assume n <= size of linked list
      ListNode slow = head, fast = head.next;
      while (n > 0) {
          fast = fast.next;
          n--;
      }
      //find the head of second part
      while (fast != null) {
          slow = slow.next;
          fast = fast.next;
      }
      second = slow.next;
      slow.next = null;
      
      //find the tail of second part
      ListNode curr = second;
      while (curr.next != null) {
          curr = curr.next;
      }
      curr.next = first;
      return second;
  }
  
  private int getLength(ListNode head) {
      if (head == null) {
          return 0;
      }
      int count = 0;
      while (head != null) {
          count++;
          head = head.next;
      }
      return count;
  }
}


/* 1 -> 2
   s       f
   first

n = 2

时间复杂度: O(n)
空间复杂度: O(1)
*/
