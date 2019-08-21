/* Given an array of integers. Find two disjoint contiguous subarrays in it such that the absolute value of the difference 
between the sums of two subarray is maximum.  Return the maximum difference.

Assumptions:

The given array is not null and has length of at least 2.
Examples:

Input: { 1, -3, 1, -4, 3, 4 }

Two subarrays: {-3, 1, -4 }, { 3, 4 }

Maximum difference = 13 */

public class Solution {
  public int maxDiff(int[] nums) {
    if (nums == null || nums.length == 0) {
      return Integer.MAX_VALUE;
    }
    //get the negative copy
    int[] copy = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      copy[i] = -1 * nums[i];
    }
    int[] leftMax = new int[nums.length];
    int[] leftMin = new int[nums.length];
    int[] rightMax = new int[nums.length];
    int[] rightMin = new int[nums.length];

    //get leftMax
    int max = Integer.MIN_VALUE;
    int sum = 0;
    int min = 0;

    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      max = Math.max(max, sum - min);
      min = Math.min(min, sum);
      leftMax[i] = max;
    }

    //get rightMax
    max = Integer.MIN_VALUE;
    sum = 0;
    min = 0;
    for (int i = nums.length - 1; i >= 0; i--) {
      sum += nums[i];
      max = Math.max(max, sum - min);
      min = Math.min(min, sum);
      rightMax[i] = max;
    }
    
    //get leftMin
    max = Integer.MIN_VALUE;
    sum = 0;
    min = 0;
    for (int i = 0; i < copy.length; i++) {
      sum += copy[i];
      max = Math.max(max, sum - min);
      min = Math.min(min, sum);
      leftMin[i] = -1 * max;
    }

    //get rightMin
    max = Integer.MIN_VALUE;
    sum = 0;
    min = 0;
    for (int i = copy.length - 1; i >= 0; i--) {
      sum += copy[i];
      max = Math.max(max, sum - min);
      min = Math.min(min, sum);
      rightMin[i] = -1 * max;
    }

    int diff = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      diff = Math.max(diff, Math.abs(leftMax[i] - rightMin[i + 1]));
      diff = Math.max(diff, Math.abs(leftMin[i] - rightMax[i + 1]));
    }
    return diff;
  }
}
//time: O(n), space: O(n)