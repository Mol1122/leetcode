public class LRUCache {
    /*
    * @param capacity: An integer
    */
    private class Node {
        Node prev;
        Node next;
        int key;
        int value;
        
        public Node (int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }
    
    int capacity = 0;
    HashMap<Integer, Node> map = new HashMap<>(); //Integer:key; Node:node
    Node head = new Node(-1, -1); //used to keep track of the order
    Node tail = new Node(-1, -1);
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node current = map.get(key);
        //remove the Node
        current.prev.next = current.next;
        current.next.prev = current.prev;
        
        move_to_tail(current);
        return map.get(key).value;
    }
    
    private void move_to_tail(Node current) {
        current.prev = tail.prev;
        current.prev.next = current;
        current.next = tail;
        tail.prev = current;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        //alrady exist in the cache
        if (get(key) != -1) { //must use the get method from LRUCache
            map.get(key).value = value;
            return;
        }
        if (map.size() == capacity) {
            map.remove(head.next.key); //delete the first one
            head.next = head.next.next;
            head.next.prev = head;
        }
        Node insert = new Node(key, value);
        move_to_tail(insert);
        map.put(key, insert);
    }
}

/* 语法：containsKey如果不存在return false, 没有contains method
** 算法：利用map去保持顺序*/