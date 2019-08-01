/* Given an array of integers, sort the elements in the array in ascending order. The selection sort algorithm should be used to solve this problem.

Examples

{1} is sorted to {1}
{1, 2, 3} is sorted to {1, 2, 3}
{3, 2, 1} is sorted to {1, 2, 3}
{4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
Corner Cases

What if the given array is null? In this case, we do not need to do anything.
What if the given array is of length zero? In this case, we do not need to do anything. */

public class Solution {
  public int[] solve(int[] nums) {
      if (nums == null || nums.length <= 1) {
          return nums;
      }
      for (int i = 0; i < nums.length - 1; i++) {
          int min = nums[i];
          int index = i;
          for (int j = i + 1; j < nums.length; j++) {
              if (nums[j] < min) {
                  min = nums[j];
                  index = j;
              }
          }
          int temp = nums[i];
          nums[i] = nums[index];
          nums[index] = temp;
      }
      return nums;
  }
}

/* 时间复杂度：O(n^2)
** 空间复杂度: O(1) */
