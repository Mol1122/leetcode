/* Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

LintCode - Online Judge Solution

Candidate Written Test Screening, Team Competency Assessment, Programming Teaching Exercises, Online Exam Grading

WeChat for information（ID chenleo0002）


The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example
Example1

Input:  nums = [1, -1, 5, -2, 3], k = 3
Output: 4
Explanation:
because the subarray [1, -1, 5, -2] sums to 3 and is the longest.
Example2

Input: nums = [-2, -1, 2, 1], k = 1
Output: 2
Explanation:
because the subarray [-1, 2] sums to 1 and is the longest. */

public class Solution {
    /**
     * @param nums: an array
     * @param k: a target value
     * @return: the maximum length of a subarray that sums to k
     */
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> sum2index = new HashMap<>();
        sum2index.put(0, -1);
        int max = 0, sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum2index.containsKey(sum - k)) {
                max = Math.max(max, i - sum2index.get(sum - k));
            }
            sum2index.putIfAbsent(sum, i);
        }
        return max;
    }
}
//time: O(n), space: O(n)