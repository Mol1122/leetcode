/* ou have a queue of integers, you need to retrieve the first unique integer in the queue.

Implement the FirstUnique class:

FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
void add(int value) insert value to the queue.
 

Example 1:

Input: 
["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
[[[2,3,5]],[],[5],[],[2],[],[3],[]]
Output: 
[null,2,null,2,null,3,null,-1]
Explanation: 
FirstUnique firstUnique = new FirstUnique([2,3,5]);
firstUnique.showFirstUnique(); // return 2
firstUnique.add(5);            // the queue is now [2,3,5,5]
firstUnique.showFirstUnique(); // return 2
firstUnique.add(2);            // the queue is now [2,3,5,5,2]
firstUnique.showFirstUnique(); // return 3
firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
firstUnique.showFirstUnique(); // return -1
Example 2:

Input: 
["FirstUnique","showFirstUnique","add","add","add","add","add","showFirstUnique"]
[[[7,7,7,7,7,7]],[],[7],[3],[3],[7],[17],[]]
Output: 
[null,-1,null,null,null,null,null,17]
Explanation: 
FirstUnique firstUnique = new FirstUnique([7,7,7,7,7,7]);
firstUnique.showFirstUnique(); // return -1
firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7]
firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3]
firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3,3]
firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7,3,3,7]
firstUnique.add(17);           // the queue is now [7,7,7,7,7,7,7,3,3,7,17]
firstUnique.showFirstUnique(); // return 17
Example 3:

Input: 
["FirstUnique","showFirstUnique","add","showFirstUnique"]
[[[809]],[],[809],[]]
Output: 
[null,809,null,-1]
Explanation: 
FirstUnique firstUnique = new FirstUnique([809]);
firstUnique.showFirstUnique(); // return 809
firstUnique.add(809);          // the queue is now [809,809]
firstUnique.showFirstUnique(); // return -1 */

class FirstUnique {
    ListNode dummy, tail;
    Map<Integer, ListNode> num2prev;
    Set<Integer> duplicates;

    public FirstUnique(int[] nums) {
        dummy = new ListNode(-1);
        tail = dummy;
        num2prev = new HashMap<>();
        duplicates = new HashSet<>();

        for (int num : nums) {
            add(num);
        }
    }
    
    public int showFirstUnique() {
        return dummy.next == null ? -1 : dummy.next.val;
    }
    
    public void add(int value) {
        if (duplicates.contains(value)) {
            return;
        }
        if (num2prev.containsKey(value)) {
            remove(value);
            duplicates.add(value);
        } else {
            ListNode newNode = new ListNode(value);
            num2prev.put(value, tail);
            tail.next = newNode;
            tail = tail.next;
        }
    }

    private void remove(int val) {
        if (!num2prev.containsKey(val)) {
            return;
        }
        ListNode prev = num2prev.get(val);
        prev.next = prev.next.next;
        num2prev.remove(val);

        if (prev.next != null) {
            num2prev.put(prev.next.val, prev);
        } else {
            tail = prev;
        }
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