/* Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

 
Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1] */

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }
        Map<Integer, Integer> num2index = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (num2index.containsKey(target - nums[i])) {
                result[0] = num2index.get(target - nums[i]);
                result[1] = i;
                return result;
            } else {
                num2index.put(nums[i], i);
            }
        }
        return result;
    }
}
//time: O(n), space: O(n)