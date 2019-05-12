/* Given a sorted integer array, remove duplicate elements. For each group of elements with the same value keep at most two of them. Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. Return the array after deduplication.

Assumptions

The given array is not null
Examples

{1, 2, 2, 3, 3, 3} → {1, 2, 2, 3, 3} */

public class Solution {
  public int[] dedup(int[] nums) {
      if (nums == null || nums.length <= 2) {
          return nums;
      }
      int i = 2;
      for (int j = 2; j < nums.length; j++) {
          if (nums[j] != nums[i - 2]) {
              nums[i] = nums[j];
              i++;
          }
      }
      int[] result = new int[i];
      for (int k = 0; k < i; k++) {
          result[k] = nums[k];
      }
      return result;
  }
}
//time: O(n), space: O(1)
