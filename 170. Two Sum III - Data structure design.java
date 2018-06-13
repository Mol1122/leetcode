class TwoSum {
    Map<Integer, Integer> map;

    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (map.containsKey(number)) {
            map.put(number, 2);
        } else {
            map.put(number, 1);
        }
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        Iterator<Integer> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            int num1 = iter.next();
            int num2 = value - num1;
            if (map.containsKey(num2)) {
                if (num1 != num2 || map.get(num2) == 2) {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */

/* 算法：add O(1), find O(n) */