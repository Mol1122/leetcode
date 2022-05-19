/* Given an array of balls with k different colors denoted by numbers 1- k, sort the balls.

Examples

k=1, {1} is sorted to {1}
k=3, {1, 3, 2, 1, 2} is sorted to {1, 1, 2, 2, 3}
k=5, {3, 1, 5, 5, 1, 4, 2} is sorted to {1, 1, 2, 3, 4, 5, 5}
Assumptions

The input array is not null.
k is guaranteed to be >= 1.
k << logn.
 */

public class Solution {
  public int[] rainbowSortIII(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return nums;
    }
    return countingSort(nums, 1, k);
  }

  private int[] countingSort(int[] nums, int lower, int upper) {
    int[] count = new int[upper - lower + 1];
    for (int num : nums) {
      count[num - lower]++;
    }
    int index = 0;
    for (int i = 0; i < count.length; i++) {
      while (count[i] > 0) {
        nums[index++] = i + lower;
        count[i]--;
      }
    }
    return nums;
  }
}
//time: O(n), space: O(1)
