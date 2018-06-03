class LRUCache {
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            prev = null;
            next = null;
        }
    }
    int capacity = 0;
    HashMap<Integer, Node> hs = new HashMap<>();
    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!hs.containsKey(key)) {
            return -1;
        }
        //remove the current
        Node current = hs.get(key);
        current.prev.next = current.next;
        current.next.prev = current.prev;
        
        move_to_tail(current);
        return hs.get(key).value;
    }
    
    public void put(int key, int value) {
        if (get(key) != -1) { //already call move_to_tail
            hs.get(key).value = value;
            return;
        }
        if (hs.size() == capacity) {
            hs.remove(head.next.key); //别忘了
            head.next = head.next.next;
            head.next.prev = head; 
        }
        Node temp = new Node(key, value);
        hs.put(key, temp);
        move_to_tail(temp);
    }
    
    private void move_to_tail(Node current) {
        current.prev = tail.prev;
        tail.prev = current;
        current.prev.next = current;
        current.next = tail;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

/* 难点：pointer的指向
** 算法：用LinkedList去保存least recently used, hashmap是为了更快找到相对应的 linkedlist Node */