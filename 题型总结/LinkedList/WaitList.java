/*
Implement a restaurant waitlist data structure. It should support the following features:

A party of customers can join the waitlist.
A previously joined party can leave the waitlist at any time.
The restaurant can ask the data structure for the first party that fits a given table size (a table size is given as an argument). time: O(n) -> O(1) use map
***same size***
*

dummy ->  group3(3)                     //group2(3)->   group1(4)->
 prev        tail

key2prev: group -> prev node
3->dummy

size2key: size -> first group

3->group3

*/

//Method 1
//space: O(n)
class WaitList {
    ListNode dummy, tail;
    int id;
    Map<Integer, ListNode> key2prev;
    Map<Integer, Integer> size2key; //Map<Integer, List<Integer>> size2keys;

    public WaitList() {
        dummy = new ListNode(-1);
        tail = dummy;
        id = 0;
        key2prev = new HashMap<>();
        size2key = new HashMap<>();
    }

    public boolean add(int key, int size) { //time: O(1)
        id++;
        if (key2prev.containsKey(id)) { 
            return false;
        }
        ListNode node = new ListNode(id, size);
        key2prev.put(id, tail);
        if (!size2key.containsKey(size)) {
            size2key.put(size, id);
        }
        tail.next = node;
        tail = tail.next;

        return true;
    }

    public boolean remove(int key) { //time: O(n)
        if (!key2prev.containsKey(key)) {
            return false;
        }
        ListNode prev = key2prev.get(key);
        int deleteKey = prev.next.key; //1
        int deleteSize = prev.next.size; //4
        prev.next = prev.next.next;

        if (prev.next != null) {
            key2prev.put(prev.next.key, prev);
        } else {
            tail = prev;
        }
        key2prev.remove(key);

        //update size2key
        if (size2key.get(deleteSize) == deleteKey) {
            //find the next group that has the same size
            updateSize2key(deleteSize);
        }
        return true;
    }

    private void updateSize2key(int size) {
        ListNode temp = dummy.next;

        while (temp != null) {
            if (temp.size == size) {
                size2key.put(size, temp.key);
                return;
            }
            temp = temp.next;
        }
        size2key.remove(size); //entire waitlist doesn't have the deleteSize
    }

    public int firstGroupWithGiveSize(int size) { // O(n)
        if (!size2key.containsKey(size)) {
            return -1;
        }
        int group = size2key.get(size);
        remove(group);

        return group;
    }
}

class ListNode { //a group of people
    int key; //unit id of that group
    int size;
    ListNode next;

    public ListNode(int key, int size) {
        this.key = key;
        this.size = size;
        this.next = null;
    }
}

//Method 2
class WaitList {
    ListNode dummy, tail;
    int id;
    Map<Integer, ListNode> key2prev;
    Map<Integer, ListNode> size2node; 

    public WaitList() {
        dummy = new ListNode(-1);
        tail = dummy;
        id = 0;
        key2prev = new HashMap<>();
        size2node = new HashMap<>();
    }

    public boolean add(int key, int size) { //time: O(n)
        id++;
        if (key2prev.containsKey(id)) { 
            return false;
        }
        ListNode node = new ListNode(id, size);
        key2prev.put(id, tail);
        if (!size2node.containsKey(size)) {
            size2node.put(size, node);
        } else {
            ListNode head = size2node.get(size);
            while (head.next != null) {
                head = head.next;
            }
            head.next = node;
        }
        tail.next = node;
        tail = tail.next;

        return true;
    }

    public boolean remove(int key) { //time: O(1)
        if (!key2prev.containsKey(key)) {
            return false;
        }
        ListNode prev = key2prev.get(key);
        int deleteKey = prev.next.key; //1
        int deleteSize = prev.next.size; //4
        prev.next = prev.next.next;

        if (prev.next != null) {
            key2prev.put(prev.next.key, prev);
        } else {
            tail = prev;
        }
        key2prev.remove(key);

        //update size2key
        if (size2node.get(deleteSize).key == deleteKey) {
            int head = size2node.get(deleteSize);
            size2node.put(deleteSize, head.next);
        }
        return true;
    }

    public int firstGroupWithGiveSize(int size) { // O(1)
        if (!size2key.containsKey(size)) {
            return -1;
        }
        int group = size2key.get(size);
        remove(group);

        return group;
    }
}

class ListNode { //a group of people
    int key; //unit id of that group
    int size;
    ListNode next;

    public ListNode(int key, int size) {
        this.key = key;
        this.size = size;
        this.next = null;
    }
}