/* Given an array of integers, move all the 0s to the right end of the array.

The relative order of the elements in the original array need to be maintained.

Assumptions:

The given array is not null.
Examples:

{1} --> {1}
{1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0} */

public class Solution {
  public int[] moveZero(int[] nums) {
      if (nums == null || nums.length == 0) {
          return nums;
      }
      int i = 0;
      for (int j = 0; j < nums.length; j++) {
          if (nums[j] != 0) {
              nums[i] = nums[j];
              i++;
          }
      }
      for (int k = i; k < nums.length; k++) {
          nums[k] = 0;
      }
      return nums;
  }
}
//time: O(n), space: O(1)