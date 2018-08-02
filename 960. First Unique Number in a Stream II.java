public class DataStream {
    class ListNode {
        int val;
        ListNode next;
        
        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
    
    Map<Integer, ListNode> numToPrev;
    Set<Integer> duplicates; //set里面保存的是曾经出现过的，不可能是唯一的数
    ListNode dummy; //linked list只保留可能是唯一的数
    ListNode tail; 
    
    public DataStream(){
        numToPrev = new HashMap<>();
        duplicates = new HashSet<>();
        dummy = new ListNode(-1);
        tail = dummy;
    }
    /**
     * @param num: next number in stream
     * @return: nothing
     */
    public void add(int num) {
        if (duplicates.contains(num)) {
            return;
        }
        if (numToPrev.containsKey(num)) {
            remove(num);
            duplicates.add(num);
        } else {
            ListNode node = new ListNode(num);
            numToPrev.put(num, tail);
            tail.next = node;
            tail = node;
        }
    }
    
    private void remove(int num) {
        ListNode prev = numToPrev.get(num);
        prev.next = prev.next.next;
        numToPrev.remove(num);
        if (prev.next != null) {
            numToPrev.put(prev.next.val, prev);
        } else {
            tail = prev;
        }
    }

    /**
     * @return: the first unique number in stream
     */
    public int firstUnique() {
        if (dummy.next != null) {
            return dummy.next.val;
        }
        return -1;
    }
}

/* 算法：因为要找first unique, 要要保存顺序就想到linked list,然后用hash能找到特定的node in O(1). 
** 难点：linked list上只保存可能是答案的数 */