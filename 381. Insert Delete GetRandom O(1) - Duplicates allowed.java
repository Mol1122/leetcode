class RandomizedCollection {
    List<Integer> result;
    Map<Integer, LinkedHashSet<Integer>> map;
    Random rand;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        result = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean alreadyExist = map.containsKey(val);
        
        map.putIfAbsent(val, new LinkedHashSet<>());
        map.get(val).add(result.size());
        result.add(val);
        
        return !alreadyExist;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        LinkedHashSet<Integer> valSet = map.get(val);
        int indexToReplace = valSet.iterator().next();
        
        int lastNum = result.get(result.size() - 1);
        LinkedHashSet<Integer> lastSet = map.get(lastNum);
        
        result.set(indexToReplace, lastNum);
        
        valSet.remove(indexToReplace);
        if (indexToReplace != result.size() - 1) {
            lastSet.remove(result.size() - 1);
            lastSet.add(indexToReplace);
        }
        result.remove(result.size() - 1); //易漏
        if (valSet.isEmpty()) {
            map.remove(val);
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return result.get(rand.nextInt(result.size()));
    }
}

/* 算法：利用LinkedHashSet去记录同一个数字出现的所有index, remove比较难，但是只要顺着思路基本就不会有错 
** 难点：最后容易忘记remove result最后一个 */