/* Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
] */

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = nums[i];
            twoSum(nums, i + 1, nums.length - 1, nums[i], -target, results);
        }
        return results;
    }
    
    private void twoSum(int[] nums, int start, int end, int number, int target, List<List<Integer>> results) {
        while (start < end) {
            if (nums[start] + nums[end] == target) {
                List<Integer> temp = Arrays.asList(number, nums[start], nums[end]);
                Collections.sort(temp);
                results.add(temp);
                start++;
                end--;
                
                while (start < end && nums[start] == nums[start - 1]) {
                    start++;
                }
                while (start < end && nums[end] == nums[end + 1]) {
                    end--;
                }
            } else if (nums[start] + nums[end] < target) {
                start++;
            } else {
                end--;
            }
        }
    }
}
//time: O(n^2), sapce: O(1)