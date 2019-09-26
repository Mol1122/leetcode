/* Design and implement a data structure for Least Recently Used (LRU) cache. It should support 
the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, 
otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache 
reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 /* capacity */ );

// cache.put(1, 1);
// cache.put(2, 2);
// cache.get(1);       // returns 1
// cache.put(3, 3);    // evicts key 2
// cache.get(2);       // returns -1 (not found)
// cache.put(4, 4);    // evicts key 1
// cache.get(1);       // returns -1 (not found)
// cache.get(3);       // returns 3
// cache.get(4);       // returns 4 */

class LRUCache {
    int size, capacity;
    Map<Integer, ListNode> num2prev;
    ListNode dummy, tail;

    public LRUCache(int capacity) {
        size = 0;
        this.capacity = capacity;
        num2prev = new HashMap<>();
        dummy = new ListNode(-1, -1);
        tail = dummy;
    }
    
    private void moveToTail(int key) {
        ListNode prev = num2prev.get(key);
        ListNode curr = prev.next;
        
        if (curr == tail) {
            return;
        }
        prev.next = prev.next.next;
        tail.next = curr;
        if (prev.next != null) {
            num2prev.put(prev.next.key, prev);
        } else {
            tail = prev;
        }
        num2prev.put(curr.key, tail);
        tail = tail.next;
    }
    
    public int get(int key) {
        if (!num2prev.containsKey(key)) {
            return -1;
        }
        int val = num2prev.get(key).next.value;
        moveToTail(key);
        return val;
    }
    
    public void put(int key, int value) {
        if (get(key) != -1) {
            ListNode prev = num2prev.get(key);
            prev.next.value = value;
            return;
        }
        if (size < capacity) {
            size++;
            ListNode newNode = new ListNode(key, value);
            tail.next = newNode;
            num2prev.put(key, tail);
            tail = tail.next;
            return;
        }
        ListNode first = dummy.next;
        num2prev.remove(first.key);
        num2prev.put(key, dummy);
        
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
        next = null;
    }
}