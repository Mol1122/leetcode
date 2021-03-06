/* Given an unsorted integer array, find the subarray that has the greatest sum. Return the sum.

Assumptions

The given array is not null and has length of at least 1.
Examples

{2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5

{-2, -1, -3}, the largest subarray sum is -1 */

public class Solution {
  public int largestSum(int[] nums) {
      if (nums == null || nums.length == 0) {
          return Integer.MAX_VALUE;
      }
      int[] f = new int[nums.length];
      f[0] = nums[0];
      int max = f[0];
    
      for (int i = 1; i < nums.length; i++) {
          f[i] = nums[i];
          if (f[i - 1] > 0) {
              f[i] += f[i - 1];
          }
          max = Math.max(max, f[i]);
      }
      return max;  
    
    //space: O(1)
/*    if (nums == null || nums.length == 0) {
          return Integer.MAX_VALUE;
      }
      int[] f = new int[2];
      f[0] = nums[0];
      int max = f[0];
    
      for (int i = 1; i < nums.length; i++) {
          f[i%2] = nums[i];
          if (f[(i - 1)%2] > 0) {
              f[i%2] += f[(i - 1)%2];
          }
          max = Math.max(max, f[i%2]);
      }
      return max;  */
    
    //prefix sum
 /*   if (nums == null || nums.length == 0) {
        return Integer.MIN_VALUE;
    }
    int max = Integer.MIN_VALUE, sum = 0, min = 0;
    for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        max = Math.max(max, sum - min);
        min = Math.min(min, sum);
    }
    return max;  */
  }
}
// f[i] = the max sum from index 0 to i, must include index i
// time: O(n), space: O(1)

/*  Follow up: How to return the left and right border of the Solution?
1. Use four temp variables: cur_left, global_left, cur_right, global_right
2. When to change cur_left, cur_right?
  - if lastMax < 0: we reset cur_left = cur_right = i
  - else: cur_right++;
3. When to change global_left or global_right?
  - when globalMax is updated, then we set:
    i. global_left = cur_left
    ii. global_right = i (cur_right)   */


public int[] largestSum(int[] nums) {
      if (nums == null || nums.length == 0) {
          return new int[0];
      }
      int global_left = 0, global_right = 0;
      int curr_left = 0, curr_right = 0;
      int[] f = new int[nums.length];
      f[0] = nums[0];
      int max = f[0];
    
      for (int i = 1; i < nums.length; i++) {
          f[i] = nums[i];
          if (f[i - 1] > 0) {
              f[i] += f[i - 1];
          } else {
              curr_left = i;
          }
          curr_right++;
          if (f[i] > max) {
              global_left = curr_left;
              global_right = curr_right;
              max = f[i];
          }
      }
      return new int[]{max, global_left, global_right};
  }