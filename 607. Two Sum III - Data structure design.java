public class TwoSum {
    /*
     * @param number: An integer
     * @return: nothing
     */
    List<Integer> list = null;
    Map<Integer, Integer> map = null;
    public TwoSum() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }
    
    public void add(int number) {
        if (map.containsKey(number)) {
            map.put(number, map.get(number) + 1);
        } else {
            map.put(number, 1);
            list.add(number);
        }
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (int i = 0; i < list.size(); i++) {
            int num1 = list.get(i), num2 = value - num1;
            if ((num1 == num2 && map.get(num1) > 1) || (num1 != num2 && map.containsKey(num2))) {
                return true;
            }
        }
        return false;
    }
}

/* 算法：用hash, add: O(log1), find：O(logn)  */