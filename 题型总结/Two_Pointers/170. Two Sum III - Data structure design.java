/* Design a data structure that accepts a stream of integers and checks if it has a pair of integers that sum up to a particular value.

Implement the TwoSum class:

TwoSum() Initializes the TwoSum object, with an empty array initially.
void add(int number) Adds number to the data structure.
boolean find(int value) Returns true if there exists any pair of numbers whose sum is equal to value, otherwise, it returns false.
 

Example 1:

Input
["TwoSum", "add", "add", "add", "find", "find"]
[[], [1], [3], [5], [4], [7]]
Output
[null, null, null, null, true, false]

Explanation
TwoSum twoSum = new TwoSum();
twoSum.add(1);   // [] --> [1]
twoSum.add(3);   // [1] --> [1,3]
twoSum.add(5);   // [1,3] --> [1,3,5]
twoSum.find(4);  // 1 + 3 = 4, return true
twoSum.find(7);  // No two integers sum up to 7, return false */

//Method1
class TwoSum {
    Map<Integer, Integer> num2count;

    public TwoSum() {
        num2count = new HashMap<>();
    }
    
    public void add(int number) {
        if (num2count.containsKey(number)) {
            num2count.put(number, num2count.get(number) + 1);
        } else {
            num2count.put(number, 1);
        }
    }
    
    public boolean find(int value) {
        for (int key : num2count.keySet()) {
            int num1 = key, num2 = value - key;
            if ((num1 == num2 && num2count.get(num1) > 1) || (num1 != num2 && num2count.containsKey(num2))) {
                return true;
            }
        }
        return false;
    }
}

//Method2
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