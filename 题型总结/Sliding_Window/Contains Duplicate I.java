/* Given an array of integers and an integer k, find out whether there are two distinct indices 
i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k. */

public class Solution {  
  public boolean containsNearbyDuplicate(int[] nums, int k) {
    //Sliding Window
    //time: O(n), space: O(k)
    if (nums == null || nums.length == 0) {
        return false;
    }
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
        if (set.contains(nums[i])) {
            return true;
        }
        set.add(nums[i]);
        if (i >= k) {
            set.remove(nums[i - k]);
        }
    }
    return false;

    //time: O(n), space: O(n)
 /*   if (nums == null || nums.length == 0) {
        return false;
    }
    Map<Integer, Integer> num2index = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        if (num2index.containsKey(nums[i])) {
            if (i - num2index.get(nums[i]) <= k) {
                return true;
            }
        } 
        num2index.put(nums[i], i);
    }
    return false;  */
  }
}

