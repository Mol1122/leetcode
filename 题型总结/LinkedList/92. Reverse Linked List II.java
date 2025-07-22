/* Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.

 

Example 1:


Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]
Example 2:

Input: head = [5], left = 1, right = 1
Output: [5]
 */

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

//Method 1
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }
        if (left == right) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = head, prev = dummy, next = null, first = head;
        int pos = 1;
        
        while (curr != null && pos != left) {
            prev = curr;
            curr = curr.next;
            pos++;
        }
        head = prev;
        first = curr;
        head.next = null;
        prev = null;
        
        while (curr != null && pos != right) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            pos++;
        }
        next = curr.next;
        curr.next = prev;
        
        head.next = curr;
        first.next = next;
        
        return dummy.next;
    }
}

//Method 2
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }
        if (left == right) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = null, curr = dummy, start = null, end = null, post = null;
        int pos = 0; 

        while (curr != null) {
            if (pos < left) {
                prev = curr;
            }    
            if (pos == left) {
                start = curr;
            }
            if (pos == right) {
                end = curr;
                post = curr.next;
                break;
            }
            curr = curr.next;
            pos++;
        }
        prev.next = null;
        end.next = null;
        reverse(start, end);
        
        prev.next = end;
        start.next = post;
        return dummy.next;
    }

    private void reverse(ListNode start, ListNode end) {
        ListNode curr = start, temp = null, prev = null;

        while (curr != null) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
    }
}