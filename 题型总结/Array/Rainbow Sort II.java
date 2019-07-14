/* Given an array of balls, where the color of the balls can only be Red, Green, Blue or Black, sort the balls such that all balls with same color are grouped together and from left to right the order is Red->Green->Blue->Black. (Red is denoted by 0, Green is denoted by 1,  Blue is denoted by 2 and Black is denoted by 3).

Examples

{0} is sorted to {0}
{1, 0} is sorted to {0, 1}
{1, 3, 1, 2, 0} is sorted to {0, 1, 1, 2, 3}
Assumptions

The input array is not null. */

public class Solution {
  public int[] rainbowSortII(int[] nums) {
    if (nums == null || nums.length == 0) {
      return nums;
    }
    int i = 0, j = 0, k = 0, t = nums.length - 1;
    while (k <= t) {
      if (nums[k] == 0) {
        swap(nums, j, k);
        swap(nums, i, j);
        i++;
        j++;
        k++;
      } else if (nums[k] == 1) {
        swap(nums, j, k);
        j++;
        k++;
      } else if (nums[k] == 2) {
        k++;
      } else if (nums[k] == 3) {
        swap(nums, k, t);
        t--;
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
//time: O(n), space: O(1)
