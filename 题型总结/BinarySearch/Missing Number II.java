/* Given an integer array of size N - 1 sorted by ascending order, containing all the numbers from 1 to N except one, find the missing number.

Assumptions

The given array is not null, and N >= 1
Examples

A = {1, 2, 4}, the missing number is 3
A = {1, 2, 3}, the missing number is 4
A = {}, the missing number is 1 */

public class Solution {
  public int missing(int[] nums) {
      if (nums.length == 0) {
          return 1;
      }
      int start = 0, end = nums.length - 1;
      while (start + 1 < end) {
          int mid = start + (end - start) / 2;
          if (nums[mid] != mid + 1) {
              end = mid;
          } else if (nums[mid] == mid + 1) {
              start = mid;
          }
      }
      if (nums[start] != start + 1) {
          return start + 1;
      } else if (nums[end] != end + 1) {
          return end + 1;
      } else {
          return nums.length + 1;
      }
  }
}
//time: O(logn), space: O(1)
