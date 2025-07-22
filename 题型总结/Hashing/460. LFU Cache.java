/* Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:

LFUCache(int capacity) Initializes the object with the capacity of the data structure.
int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.

When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.

The functions get and put must each run in O(1) average time complexity.

 

Example 1:

Input
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

Explanation
// cnt(x) = the use counter for key x
// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // return 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                 // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // return 4
                 // cache=[4,3], cnt(4)=2, cnt(3)=3
 */

 class LFUCache {
    Map<Integer, Integer> key2val;
    Map<Integer, Integer> key2freq;
    Map<Integer, LinkedHashSet<Integer>> freq2keys;
    int capacity, minFreq;

    public LFUCache(int capacity) {
        key2val = new HashMap<>();
        key2freq = new HashMap<>();
        freq2keys = new HashMap<>();
        this.capacity = capacity;
        minFreq = 0;
    }
    
    public int get(int key) {
        if (!key2val.containsKey(key)) {
            return -1;
        }
        int freq = key2freq.get(key);
        freq2keys.get(freq).remove(key);
        if (freq2keys.get(freq).isEmpty()) {
            freq2keys.remove(freq);
        }
        putFreq(key, freq + 1);

        return key2val.get(key);
    }
    
    public void put(int key, int value) {
        if (capacity == 0) {s
            return;
        }
        if (key2val.containsKey(key)) {
            key2val.put(key, value);
            get(key);
            return;
        }
        if (key2val.size() == capacity) {
            //need to remove the key with least freq          
            minFreq = Collections.min(freq2keys.keySet());
            int key2remove = freq2keys.get(minFreq).iterator().next();
            freq2keys.get(minFreq).remove(key2remove);
            key2freq.remove(key2remove);
            key2val.remove(key2remove);
        }
        key2val.put(key, value);
        putFreq(key, 1);
    }

    private void putFreq(int key, int freq) {
        key2freq.put(key, freq);
        freq2keys.putIfAbsent(freq, new LinkedHashSet<>());
        freq2keys.get(freq).add(key);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */