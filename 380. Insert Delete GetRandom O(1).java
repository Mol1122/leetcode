class RandomizedSet {
    ArrayList<Integer> nums;
    HashMap<Integer, Integer> map;
    Random rand;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        nums = new ArrayList<Integer>();
        map = new HashMap<Integer, Integer>(); //val, index of val in nums
        rand = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            map.put(val, nums.size());
            nums.add(val);
            return true;
        }
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        } else {
            int index = map.get(val);
            if (index != nums.size() - 1) { //not the last element, make it become the last one and delete it
                int last = nums.get(nums.size() - 1);
                map.put(last, index);
                nums.set(index, last);
            }
            map.remove(val);
            nums.remove(nums.size() - 1);
            return true;
        }
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

/* 语法：rand.nextInt(size)
** 算法：用一个ArrayList去保存所有的数，用hashmap去记录数值和对应在ArrayList里的index。 remove的时候要换到最后的位置是为了节省空间 */