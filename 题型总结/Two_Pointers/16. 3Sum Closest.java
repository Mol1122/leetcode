/* Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.

Return the sum of the three integers.

You may assume that each input would have exactly one solution.

 

Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
Example 2:

Input: nums = [0,0,0], target = 1
Output: 0 */

//Method 1
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return -1;
        }
        Arrays.sort(nums);
        int bestSum = nums[0] + nums[1] + nums[2];
        
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(bestSum - target)) {
                    bestSum = sum;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return bestSum;
    }
}

//Method 2
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int[] sum = {0}, diff = {Integer.MAX_VALUE};
        
        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            twoSum(nums, i + 1, nums.length - 1, nums[i], sum, diff, target);
        }
        return sum[0];
    }

    private void twoSum(int[] nums, int left, int right, int num1, int[] sum, int[] diff, int target) {
        while (left < right) {
            int temp = num1 + nums[left] + nums[right];
            if (Math.abs(temp - target) < diff[0]) {
                sum[0] = temp;
                diff[0] = Math.abs(temp - target);
            }
            if (temp < target) {
                left++;
            } else {
                right--;
            }
        }
    }
}