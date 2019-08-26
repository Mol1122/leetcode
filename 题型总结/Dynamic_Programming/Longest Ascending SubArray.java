/* Given an unsorted array, find the length of the longest subarray in which the numbers are in ascending order.

Assumptions

The given array is not null
Examples

{7, 2, 3, 1, 5, 8, 9, 6}, longest ascending subarray is {1, 5, 8, 9}, length is 4.

{1, 2, 3, 3, 4, 4, 5}, longest ascending subarray is {1, 2, 3}, length is 3. */

public class Solution {
  public int longest(int[] nums) {
      if (nums == null || nums.length == 0) {
          return 0;
      }
      int[] f = new int[nums.length];
      f[0] = 1;
      int max = 1;
      for (int i = 1; i < nums.length; i++) {
          if (nums[i] > nums[i - 1]) {
              f[i] = f[i - 1] + 1;
          } else {
              f[i] = 1;
          }
          max = Math.max(max, f[i]);
      }
      return max;
  }
}
//f[i] = the longest subarray ending at index i
//time: O(n), space: 可以优化到O(1)
// 7, 2, 3, 1, 5, 8, 9, 6
//          i     i