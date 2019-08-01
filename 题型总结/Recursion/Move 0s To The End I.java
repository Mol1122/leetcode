/* Given an array of integers, move all the 0s to the right end of the array.

The relative order of the elements in the original array does not need to be maintained.

Assumptions:

The given array is not null.
Examples:

{1} --> {1}
{1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0} or {1, 1, 3, 0, 0} or {3, 1, 1, 0, 0} */

public class Solution {
  public int[] moveZero(int[] nums) {
    if (nums == null || nums.length == 0) {
      return nums;
    }
    int i = 0, j = nums.length - 1;

    while (i <= j) {
      if (nums[i] != 0) {
        i++;
      } else {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        j--;
      }
    }
    return nums;
  }
}
/* 算法：两个挡板，三个区域
[0, i) -> 数字
[i, j] -> unexplored area
(j, nums.length - 1] -> 0

时间复杂度: O(n)
空间复杂度：O(1)
*/

