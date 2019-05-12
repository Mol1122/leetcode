/* Given a sorted integer array, remove duplicate elements. For each group of elements with the same value keep only one of them. 
Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. 
Return the array after deduplication.

Assumptions

The array is not null
Examples

{1, 2, 2, 3, 3, 3} → {1, 2, 3} */

public class Solution {
  public int[] dedup(int[] nums) {
      if (nums == null || nums.length == 0) {
          return nums;
      }
      int i = 1;
      for (int j = 1; j < nums.length; j++) {
          if (nums[j] != nums[i - 1]) {
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
// [0, i): nums we want to keep
// [i, j]: nums we ingore
// (j, n]: haven't explored
// time: O(n), space: O(1)