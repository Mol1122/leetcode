/* Given an integer array of length L, find all numbers that occur more than 1/K * L times if any exist.

Assumptions

The given array is not null or empty
K >= 2
Examples

A = {1, 2, 1, 2, 1}, K = 3, return [1, 2]
A = {1, 2, 1, 2, 3, 3, 1}, K = 4, return [1, 2, 3]
A = {2, 1}, K = 2, return []  */

//Method 1
public class Solution {
  public List<Integer> majority(int[] nums, int k) {
    Set<Integer> results = new HashSet<>();
    if (nums == null || nums.length < k) {
        for (int num : nums) {
            results.add(num);
        }
        return new ArrayList<>(results);
    }
    Map<Integer, Integer> num2count = new HashMap<>();
    
    for (int i = 0; i < nums.length; i++) {
        if (num2count.containsKey(nums[i])) {
            num2count.put(nums[i], num2count.get(nums[i]) + 1);
        } else {
            if (num2count.size() < k - 1) {
                num2count.put(nums[i], 1);
            } else {
                Iterator<Map.Entry<Integer, Integer>> it = num2count.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Integer, Integer> entry = it.next();
                    entry.setValue(entry.getValue() - 1);
                    if (entry.getValue() == 0) {
                        it.remove();
                    }
                }
            }
        }
    }
    for (int key : num2count.keySet()) {
        num2count.put(key, 0);
    }
    for (int num : nums) {
        if (num2count.containsKey(num)) {
            num2count.put(num, num2count.get(num) + 1);
        }
    }
    for (int key : num2count.keySet()) {
        if (num2count.get(key) > nums.length / k) {
            results.add(key);
        }
    }
    return new ArrayList<>(results);
  }
}
//time: O(n), space: O(k)

//Method 2
public class Solution {
  public List<Integer> majority(int[] nums, int k) {
    List<Integer> results = new ArrayList<>();
    if (nums == null || nums.length == 0) {
      return results;
    }
    Map<Integer, Integer> num2count = new HashMap<>();

    for (int num : nums) {
      if (num2count.containsKey(num)) {
        num2count.put(num, num2count.get(num) + 1);
      } else if (num2count.size() < k) { //better to use k - 1
        num2count.put(num, 1);
      } else {
        Iterator<Map.Entry<Integer, Integer>> it = num2count.entrySet().iterator();
        while (it.hasNext()) {
          Map.Entry<Integer, Integer> entry = it.next();
          entry.setValue(entry.getValue() - 1);
          if (entry.getValue() == 0) {
            it.remove();
          }
        }
      }
    }
    for (int key : num2count.keySet()) {
      num2count.put(key, 0);
    }
    for (int num : nums) {
      if (num2count.containsKey(num)) {
        num2count.put(num, num2count.get(num) + 1);
      }
    }
    for (int key : num2count.keySet()) {
      if (num2count.get(key) > nums.length / k) {
        results.add(key);
      }
    }
    return results;
  }
}
//time: O(n), space: O(k)