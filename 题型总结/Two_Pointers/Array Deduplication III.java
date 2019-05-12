/* Given a sorted integer array, remove duplicate elements. For each group of elements with the same value do not keep any of them. Do this in-place, using the left side of the original array and and maintain the relative order of the elements of the array. Return the array after deduplication.

Assumptions

The given array is not null
Examples

{1, 2, 2, 3, 3, 3} → {1}  */

public class Solution {
  public int[] dedup(int[] nums) {
      if (nums == null || nums.length <= 1) {
          return nums;
      }
      int i = 0;
      boolean flag = false;
      for (int j = 1; j < nums.length; j++) {
          if (nums[j] == nums[i]) {
              flag = true;
          } else if (flag == true) {
              nums[i] = nums[j];
              flag = false;
          } else {
              i++;
              nums[i] = nums[j];
          }
      }
      return Arrays.copyOf(nums, flag ? i : i + 1);
  }
}
//time: O(n), space: O(1)