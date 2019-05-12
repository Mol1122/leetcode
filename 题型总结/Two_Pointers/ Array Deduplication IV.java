/* Given an unsorted integer array, remove adjacent duplicate elements repeatedly, from left to right. For each group of elements with the same value do not keep any of them.

Do this in-place, using the left side of the original array. Return the array after deduplication.

Assumptions

The given array is not null
Examples

{1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1} */

public class Solution {
  public int[] dedup(int[] nums) {
      if (nums == null || nums.length == 0) {
          return nums;
      }
      int end = -1;
      for (int i = 0; i < nums.length; i++) {
          if (end == -1 || nums[i] != nums[end]) {
              end++;
              nums[end] = nums[i];
          } else {
              while (i + 1 < nums.length && nums[i + 1] == nums[end]) {
                  i++;
              }
              end--;
          }
      }
      return Arrays.copyOf(nums, end + 1);
  }
}
/* 算法：利用stack思想，一开始end是-1，说明stack是空的。指针不断向后走，看看是否能加进stack */
//time: O(n), space: O(1)