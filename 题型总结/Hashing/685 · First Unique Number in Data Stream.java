/* Given a continuous stream of data, write a function that returns the first unique number (including the last number) when the terminating number arrives. If the terminating number is not found, return -1.

LintCode - Online Judge Solution

Candidate Written Test Screening, Team Competency Assessment, Programming Teaching Exercises, Online Exam Grading

WeChat for information（ID chenleo0002）


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
    
    public void add(int val) {
        if (duplicates.contains(val)) {
            return;
        }
        if (numToPrev.containsKey(val)) {
            remove(val);
            duplicates.add(val);
        } else {
            ListNode newNode = new ListNode(val);
            numToPrev.put(val, tail);
            tail.next = newNode;
            tail = tail.next;
        }
    }
    
    private void remove(int number) {
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
        return dummy.next == null ? -1 : dummy.next.val;
    }
}

class ListNode {
    int val;
    ListNode next;
    
    public ListNode(int val) {
        this.val = val;
        next = null;
    }
}