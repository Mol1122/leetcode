/* Given an array of integers (without any duplicates), shuffle the array such that all permutations are equally likely to be generated.

Assumptions

The given array is not null */

public class Solution {
  public void shuffle(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return;
    }
    int n = nums.length;
    Random rand = new Random();
    for (int i = 0; i < nums.length; i++) {
      int j = rand.nextInt(n - i) + i;
      swap(nums, i, j);
    }
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}