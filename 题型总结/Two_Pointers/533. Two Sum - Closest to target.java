/* Given an array nums of n integers, find two integers in nums such that the sum is closest to a given number, target.

Return the absolute value of difference between the sum of the two numbers and the target.

Example
Example1

Input:  nums = [-1, 2, 1, -4] and target = 4
Output: 1
Explanation:
The minimum difference is 1. (4 - (2 + 1) = 1).
Example2

Input:  nums = [-1, -1, -1, -4] and target = 4
Output: 6
Explanation:
The minimum difference is 6. (4 - (- 1 - 1) = 6). */

public class Solution {
    /**
     * @param nums: an integer array
     * @param target: An integer
     * @return: the difference between the sum and the target
     */
    public int twoSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        int diff = Integer.MAX_VALUE;
        int left = 0, right = nums.length - 1;
        Arrays.sort(nums);
        
        while (left < right) {
            int temp = Math.abs(target - (nums[left] + nums[right]));
            if (nums[left] + nums[right] < target) {
                diff = Math.min(diff, temp);
                left++;
            } else {
                diff = Math.min(diff, temp);
                right--;
            }
        }
        
        return diff;
    }
}