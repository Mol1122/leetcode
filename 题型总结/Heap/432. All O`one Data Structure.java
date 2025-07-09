/* Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.

Implement the AllOne class:

AllOne() Initializes the object of the data structure.
inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
Note that each function must run in O(1) average time complexity.

 

Example 1:

Input
["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
Output
[null, null, null, "hello", "hello", null, "hello", "leet"]

Explanation
AllOne allOne = new AllOne();
allOne.inc("hello");
allOne.inc("hello");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "hello"
allOne.inc("leet");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "leet" */

class AllOne {
    Map<String, Integer> str2count;
    TreeSet<Pair> treeset; //balanced BST based on count

    public AllOne() {
        str2count = new HashMap<>();
        treeset = new TreeSet<>(new Comparator<Pair>(){
            public int compare(Pair a, Pair b) {
                return a.count == b.count ? a.str.compareTo(b.str) : a.count - b.count;
            }
        });
    }
    
    public void inc(String key) {
        if (!str2count.containsKey(key)) {
            str2count.put(key, 1);
            treeset.add(new Pair(key, 1));
        } else {
            treeset.remove(new Pair(key, str2count.get(key)));
            treeset.add(new Pair(key, str2count.get(key) + 1));
            str2count.put(key, str2count.get(key) + 1);
        }
    }
    
    public void dec(String key) {
        if (!str2count.containsKey(key)) {
            return;
        }

        treeset.remove(new Pair(key, str2count.get(key)));
        if (str2count.get(key) == 1) { //after decrement would be 0
            str2count.remove(key);
        } else {
            treeset.add(new Pair(key, str2count.get(key) - 1));
            str2count.put(key, str2count.get(key) - 1);
        }
    }
    
    public String getMaxKey() {
        return treeset.isEmpty() ? "" : treeset.last().str;
    }
    
    public String getMinKey() {
        return treeset.isEmpty() ? "" : treeset.first().str;
    }
}

class Pair {
    String str;
    int count;

    public Pair(String str, int count) {
        this.str = str;
        this.count = count;
    }
}

/* Time Complexity: Each operation (inc, dec, getMaxKey, getMinKey) runs in O(1) on average, as insertion and deletion in a set (backed by a balanced binary search tree) are logarithmic but constant on average due to limited keys and operations.
Space Complexity: O(N), where N is the number of unique keys. */