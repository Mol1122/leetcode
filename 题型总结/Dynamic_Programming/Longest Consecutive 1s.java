/* Given an array containing only 0s and 1s, find the length of the longest 
subarray of consecutive 1s.

Assumptions

The given array is not null
Examples

{0, 1, 0, 1, 1, 1, 0}, the longest consecutive 1s is 3. */

public class Solution {
  public int longest(int[] nums) {
      if (nums == null || nums.length == 0) {
          return 0;
      }
      int[] f = new int[nums.length];
      f[0] = nums[0];
      
      int max = f[0];
      for (int i = 1; i < nums.length; i++) {
          if (nums[i] == 0) {
              f[i] = 0;
          } else {
              f[i] = f[i - 1] + 1;
          }
          max = Math.max(max, f[i]);
      }
      return max;
  }
}
//f[i] = max length of subarray that contains 1s, must include index i (因为subarray是连续的)
//time: O(n), space: O(n)
