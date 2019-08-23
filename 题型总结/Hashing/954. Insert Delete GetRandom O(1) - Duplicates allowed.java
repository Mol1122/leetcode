/* Design a data structure that supports all following operations in average O(1) time.

Example
Example 1:

Input:
insert(1)
insert(1)
insert(2)
getRandom()
remove(1)

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();
Example 2:

Input:
insert(1)
insert(1)
getRandom()
remove(1)
Notice
Duplicate elements are allowed.

insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains. */

class RandomizedCollection {
    List<Integer> nums;
    Map<Integer, LinkedHashSet<Integer>> num2Index;
    Random rand;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        nums = new ArrayList<>();
        num2Index = new HashMap<>();
        rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean alreadyExist = num2Index.containsKey(val);
        num2Index.putIfAbsent(val, new LinkedHashSet<>());
        num2Index.get(val).add(nums.size());
        nums.add(val);
        
        return !alreadyExist;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!num2Index.containsKey(val)) {
            return false;
        }
        LinkedHashSet<Integer> valSet = num2Index.get(val);
        int indexToReplace = valSet.iterator().next();
        
        int last = nums.get(nums.size() - 1);
        LinkedHashSet<Integer> lastSet = num2Index.get(last);
        
        nums.set(indexToReplace, last);
        valSet.remove(indexToReplace);
        
        if (indexToReplace != nums.size() - 1) {
            lastSet.remove(nums.size() - 1);
            lastSet.add(indexToReplace);
        }
        if (valSet.isEmpty()) {
            num2Index.remove(val);
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}

/* 允许重复的数(面试的时候99%的人做不出来，所以给个思路就可以) 用LinkedHashSet去存同一个数所有的index  */
