class LRUCache {
    class ListNode {
        int key, val;
        ListNode next;
        
        public ListNode(int key, int value) {
            this.key = key;
            this.val = value;
            this.next = null;
        }
    }
    int capacity, size;
    Map<Integer, ListNode> keyToPrev;
    ListNode dummy, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        keyToPrev = new HashMap<>();
        dummy = new ListNode(0, 0);
        tail = dummy;
    }
    
    public int get(int key) {
        if (!keyToPrev.containsKey(key)) {
            return -1;
        }
        moveToTail(key);
        return tail.val;
    }
    
    private void moveToTail(int key) {
        ListNode prev = keyToPrev.get(key);
        ListNode curr = prev.next;
        
        if (tail == curr) {
            return;
        }
        prev.next = prev.next.next;
        tail.next = curr;
        if (prev.next != null) {
            keyToPrev.put(prev.next.key, prev);
        }
        keyToPrev.put(key, tail);
        tail = curr;
    }
    
    public void put(int key, int value) {
        if (get(key) != -1) {
            ListNode prev = keyToPrev.get(key);
            prev.next.val = value;
           // moveToTail(key);
            return;
        }
        if (size < capacity) {
            size++;
            ListNode node = new ListNode(key, value);
            tail.next = node;
            keyToPrev.put(key, tail);
            tail = node;
            return;
        }
        ListNode first = dummy.next;
        keyToPrev.remove(first.key);
        
        first.key = key;
        first.val = value;
        keyToPrev.put(key, dummy);
        
        moveToTail(key);
    }
}

/* 算法：因为每次更新都往后append,所以想到用linked list,然后因为要快速找到listnode,所以想到用hash.
** 难点：1.moveToTail的时候要判断tail == curr 2.每次放入hash的要是key,不能是val*/