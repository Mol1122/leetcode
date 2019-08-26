/* Given a continuous stream of data, write a function that returns the first unique number (including the terminating number) when the terminating number arrives. If the unique number is not found, return -1.

Example
Example1

Input: 
[1, 2, 2, 1, 3, 4, 4, 5, 6]
5
Output: 3
Example2

Input: 
[1, 2, 2, 1, 3, 4, 4, 5, 6]
7
Output: -1
Example3

Input: 
[1, 2, 2, 1, 3, 4]
3
Output: 3 */

public class Solution {
    /**
     * @param nums: a continuous stream of numbers
     * @param number: a number
     * @return: returns the first unique number
     */
    public int firstUniqueNumber(int[] nums, int number) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        DataStream ds = new DataStream();
        for (int i = 0; i < nums.length; i++) {
            ds.add(nums[i]);
            if (nums[i] == number) {
                return ds.firstUnique();
            }
        }
        return -1;
    }
}

class DataStream {
    Map<Integer, ListNode> numToPrev;
    Set<Integer> duplicates;
    ListNode dummy, tail;
    
    public DataStream() {
        numToPrev = new HashMap<>();
        duplicates = new HashSet<>();
        dummy = new ListNode(-1);
        tail = dummy;
    }
    
    public void add(int number) {
        if (duplicates.contains(number)) {
            return;
        }
        if (numToPrev.containsKey(number)) {
            remove(number);
            duplicates.add(number);
        } else {
            ListNode node = new ListNode(number);
            numToPrev.put(number, tail);
            tail.next = node;
            tail = node;
        }
    }
    
    public void remove(int number) {
        if (!numToPrev.containsKey(number)) {
            return;
        }
        ListNode prev = numToPrev.get(number);
        prev.next = prev.next.next;
        numToPrev.remove(number);
        
        if (prev.next != null) {
            numToPrev.put(prev.next.val, prev);
        } else {
            tail = prev;
        }
    }
    
    public int firstUnique() {
        if (dummy.next != null) {
            return dummy.next.val;
        }
        return -1;
    }
}

class ListNode {
    int val;
    ListNode next;
    
    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}