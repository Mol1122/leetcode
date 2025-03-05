/* Given an array nums of integers and integer k, return the maximum sum such that there exists i < j with nums[i] + nums[j] = sum and sum < k. If no i, j exist satisfying this equation, return -1.


Example 1:

Input: nums = [34,23,1,24,75,33,54,8], k = 60
Output: 58
Explanation: We can use 34 and 24 to sum 58 which is less than 60.
Example 2:

Input: nums = [10,20,30], k = 15
Output: -1
Explanation: In this case it is not possible to get a pair sum less that 15. */

class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        Arrays.sort(nums);
        int start = 0, end = nums.length - 1, ans = Integer.MIN_VALUE;

        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum < k) {
                ans = Math.max(ans, sum);
            }
            if (sum < k) {
                start++;
            } else {
                end--;
            }
        }
        return ans == Integer.MIN_VALUE ? -1 : ans;
    }
}
//time: O(nlogn), space: O(n)