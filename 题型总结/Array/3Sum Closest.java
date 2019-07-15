/* Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the difference  between the sum of the three integers and the given number. You may assume that each input would have exactly one solution.

Example

           For example, given array S = {-1 2 1 -4}, and target = 1.

          The sum that is closest to the target is 2. (-1 + 2 + 1 = 2) and the difference is 1. */

public class Solution {
  public int threeSumClosest(int[] nums, int target) {
    if (nums == null || nums.length < 3) {
      return -1;
    }
    Arrays.sort(nums);
    int bestSum = nums[0] + nums[1] + nums[2];

    for (int i = 0; i <= nums.length; i++) {
      int left = i + 1, right = nums.length - 1;
      while (left < right) {
        int sum = nums[i] + nums[left] + nums[right];
        if (Math.abs(sum - target) < Math.abs(bestSum - target)) {
          bestSum = sum;
        }
        if (sum == target) {
          return 0;
        } else if (sum < target) {
          left++;
        } else {
          right--;
        }
      }
    }
    return Math.abs(bestSum - target);
  }
}
//time: O(n^2), space: O(1)