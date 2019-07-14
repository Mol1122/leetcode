/* Given an array of integers, sort the elements in the array in ascending order. The insertion sort algorithm should be used to solve this problem.

Examples

{1, 2, 3} is sorted to {1, 2, 3}
{4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
Corner Cases

What if the given array is null? In this case, we do not need to do anything.
What if the given array is of length zero? In this case, we do not need to do anything. */

public class Solution {
  public int[] sort(int[] nums) {
    if (nums == null || nums.length == 0) {
      return nums;
    }
    for (int i = 0; i < nums.length; i++) {
      int key = nums[i];
      int j = i - 1;
      while (j >= 0 && nums[j] > key) {
        nums[j + 1] = nums[j];
        j--;
      }
      nums[j + 1] = key;
    }
    return nums;
  }
}
//time: O(n^2), space: O(1)
