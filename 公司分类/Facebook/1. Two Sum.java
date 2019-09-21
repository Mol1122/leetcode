/* Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1]. */

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }
        Map<Integer, Integer> val2index = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (val2index.containsKey(target - nums[i])) {
                result[0] = val2index.get(target - nums[i]);
                result[1] = i;
                return result;
            }
            val2index.put(nums[i], i);
        }
        return result;
    }
}
//time: O(n), space: O(n)