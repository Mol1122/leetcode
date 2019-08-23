/* Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example
// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom(); */

public class RandomizedSet {
    List<Integer> nums;
    Map<Integer, Integer> num2Index;
    Random rand;
    
    public RandomizedSet() {
        nums = new ArrayList<>();
        num2Index = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (num2Index.containsKey(val)) {
            return false;
        }
        num2Index.put(val, nums.size());
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!num2Index.containsKey(val)) {
            return false;
        }
        int index = num2Index.get(val);
        int last = nums.get(nums.size() - 1);
        num2Index.put(last, index);
        nums.set(index, last);
        nums.remove(nums.size() - 1);
        num2Index.remove(val);
        return true;
    }

    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param = obj.insert(val);
 * boolean param = obj.remove(val);
 * int param = obj.getRandom();
 */