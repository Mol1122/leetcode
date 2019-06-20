/* Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers. If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
The replacement must be in-place, do not allocate extra memory.  

Example

1,2,3 → 1,3,2

3,2,1 → 1,2,3

1,1,5 → 1,5,1 */

public class Solution {
  public int[] nextPermutation(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return nums;
    }
    int n = nums.length;
    int i = n - 1;
    while (i > 0 && nums[i] <= nums[i - 1]) {
      i--;
    }
    if (i != 0) {
      int j = n - 1;
      while (nums[j] <= nums[i - 1]) {
        j--;
      }
      swapItem(nums, j, i - 1);
    }
    swapList(nums, i, n - 1);
    return nums;
  }

  private void swapItem(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  private void swapList(int[] nums, int i, int j) {
    while (i < j) {
      swapItem(nums, i, j);
      i++;
      j--;
    }
  }
}
//time: O(n), space: O(1)