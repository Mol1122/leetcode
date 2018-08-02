class RandomizedSet {
    List<Integer> nums;
    Map<Integer, Integer> num2index;
    Random rand;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        nums = new ArrayList<>();
        num2index = new HashMap<>();
        rand = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (num2index.containsKey(val)) {
            return false;
        }
        num2index.put(val, nums.size());
        nums.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!num2index.containsKey(val)) {
            return false;
        }
        int index = num2index.get(val);
        if (index != nums.size() - 1) {
            int value = nums.get(nums.size() - 1);
            num2index.put(value, index);
            nums.set(index, value);
        }
        num2index.remove(val);
        nums.remove(nums.size() - 1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}