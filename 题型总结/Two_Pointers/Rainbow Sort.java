/* Given an array of balls, where the color of the balls can only be Red, Green or Blue, sort the balls such that 
all the Red balls are grouped on the left side, all the Green balls are grouped in the middle and all the Blue 
balls are grouped on the right side. (Red is denoted by -1, Green is denoted by 0, and Blue is denoted by 1).

Examples

{0} is sorted to {0}
{1, 0} is sorted to {0, 1}
{1, 0, 1, -1, 0} is sorted to {-1, 0, 0, 1, 1}
Assumptions

The input array is not null.
Corner Cases

What if the input array is of length zero? In this case, we should not do anything as well. */

public class Solution {
  public int[] rainbowSort(int[] nums) {
    if (nums == null || nums.length == 0) {
      return nums;
    }
    int i = 0, j = 0, k = nums.length - 1;

    while (j <= k) {
      if (nums[j] == -1) {
        swap(nums, i, j);
        i++;
        j++;
      } else if (nums[j] == 0) {
        j++;
      } else {
        swap(nums, j, k);
        k--;
      }
    }
    return nums;
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}
//i的左边都是-1, j的左边都是0, k的右边都是1. [j, k]中间是还没有探索的
//time: O(n), space: O(1)