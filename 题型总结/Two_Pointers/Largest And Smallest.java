/* Use the least number of comparisons to get the largest and smallest number in the given integer array. Return the largest number and the smallest number.

Assumptions

The given array is not null and has length of at least 1
Examples

{2, 1, 5, 4, 3}, the largest number is 5 and smallest number is 1. return [5, 1]. */

public class Solution {
  public int[] largestAndSmallest(int[] nums) {
      if (nums == null || nums.length == 0) {
          return nums;
      }
      int n = nums.length;
      for (int i = 0; i < n / 2; i++) {
          if (nums[i] < nums[n - 1 - i]) {
              swap(nums, i, n - 1 - i);
          }
      }
      return new int[]{getLargest(nums, 0, (n - 1) / 2), getSmallest(nums, n / 2, n - 1)};
  }
  
  private int getLargest(int[] nums, int start, int end) {
      int max = nums[start];
      for (int i = start + 1; i <= end; i++) {
          max = Math.max(max, nums[i]);
      }
      return max;
  }
  
  private int getSmallest(int[] nums, int start, int end) {
      int min = nums[start];
      for (int i = start + 1; i <= end; i++) {
          min = Math.min(min, nums[i]);
      }
      return min;
  }
  
  private void swap(int[] nums, int i, int j) {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
  }
}

//time: O(n), space: O(1)
//space: O()
