/* Given an integer array, determine how many pairs of elements, the absolute value of the difference between the two elements is the given target value.

Assumptions:

There could be elements with duplicate value in the array, and each of the elements is considered different.
The given array is not null and has length >= 2.
Examples:

array = {3, 1, 2, 1}, target = 2, there are 2 pairs { (3, 1), (3, 1) } */

public class Solution {
  public int pairs(int[] nums, int target) {
    if (nums == null || nums.length < 2) {
      return 0;
    }
    Arrays.sort(nums);
    int count = 0;
    int i = 0, j = 0;

    while (i < nums.length && j < nums.length) {
      int diff = nums[j] - nums[i];
      if (diff < target) {
        j++;
      } else if (diff > target) {
        i++;
      } else {
        if (i != j) {
          count++;
          i++;
        } else {
          j++;
        }
      }
    }
    return count;
  }
}
//time: O(nlogn), space: O(1)