/* Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:

MyHashMap() initializes the object with an empty map.
void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 

Example 1:

Input
["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
Output
[null, null, null, 1, -1, null, 1, null, -1]

Explanation
MyHashMap myHashMap = new MyHashMap();
myHashMap.put(1, 1); // The map is now [[1,1]]
myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]] */

class MyHashMap {
    List<Pair> list;
    Map<Integer, Integer> key2index;

    public MyHashMap() {
        list = new ArrayList<>();
        key2index = new HashMap<>();
    }
    
    public void put(int key, int value) {
        Pair p = new Pair(key, value);
        key2index.put(key, list.size());
        list.add(p);
    }
    
    public int get(int key) {
        if (!key2index.containsKey(key)) {
            return -1;
        }
        int index = key2index.get(key);
        return list.get(index).value;
    }
    
    public void remove(int key) {
        if (!key2index.containsKey(key)) {
            return;
        }
        int index = key2index.get(key);
        if (index == list.size() - 1) {
            list.remove(list.size() - 1);
            key2index.remove(key);
            return;
        }
        Pair last = list.get(list.size() - 1);
        list.set(index, last);
        key2index.put(last.key, index);
        key2index.remove(key);
        list.remove(list.size() - 1);
    }
}

class Pair {
    int key, value;

    public Pair(int key, int value) {
        this.key = key;
        this.value = value;
    }
}