/* Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2  );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4 */

class LRUCache {
    int size, capacity;
    Map<Integer, ListNode> keyToPrev;
    ListNode dummy, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        keyToPrev = new HashMap<>();
        dummy = new ListNode(-1, -1);
        tail = dummy;
    }
    
    private void moveToTail(int key) {
        ListNode prev = keyToPrev.get(key);
        ListNode curr = prev.next;
        
        if (curr == tail) {
            return;
        }
        prev.next = prev.next.next;
        tail.next = curr;
        if (prev.next != null) {
            keyToPrev.put(prev.next.key, prev);
        } else {
            tail = prev;
        }
        keyToPrev.put(curr.key, tail);
        tail = tail.next;
    }
    
    //time: O(1)
    public int get(int key) {
        if (!keyToPrev.containsKey(key)) {
            return -1;
        }
        int value = keyToPrev.get(key).next.value;
        moveToTail(key);
        return value;
    }
    
    //time: O(1)
    public void put(int key, int value) {
        if (get(key) != -1) {
            ListNode prev = keyToPrev.get(key);
            prev.next.value = value;
            moveToTail(key);
            return;
        }
        if (size < capacity) {
            size++;
            ListNode newNode = new ListNode(key, value);
            tail.next = newNode;
            keyToPrev.put(key, tail);
            tail = tail.next;
            return;
        }
        ListNode first = dummy.next;
        keyToPrev.remove(first.key);
        keyToPrev.put(key, dummy);
        
        first.key = key;
        first.value = value;
        moveToTail(key);
    }
}

class ListNode {
    int key, value;
    ListNode next;
    
    public ListNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}