/* Given an array with both positive and negative numbers in random order. Shuffle the array so that positive and negative numbers are put in position with even and odd indices, respectively.

If there are more positive/negative numbers, put them at the end of the array. The ordering of positive/negative numbers does not matter.

Assumptions:

The given array is not null.
There is no 0 in the array.
Examples:

{1, 2, 3, 4, 5, -1, -1, -1} --> {1, -1, 2, -1, 3, -1, 4, 5}  (The ordering of positive/negative numbers do not matter) */

public class Solution {
  public int[] interleave(int[] nums) {
    if (nums == null || nums.length == 0) {
      return nums;
    }
    int mid = partition(nums, 0, nums.length - 1); //index of last positive
    int[] results = new int[nums.length];
    int index = 0, i = 0, j = mid + 1;
    while (i <= mid && j <= nums.length - 1) {
      results[index++] = nums[i++];
      results[index++] = nums[j++];
    }
    while (i <= mid) {
      results[index++] = nums[i++];
    }
    while (j <= nums.length - 1) {
      results[index++] = nums[j++];
    }
    return results;
  }

  private int partition(int[] nums, int start, int end) {
    while (start <= end) {
      while (start <= end && nums[start] > 0) {
        start++;
      }
      while (start <= end && nums[end] < 0) {
        end--;
      }
      if (start <= end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
        start++;
        end--;
      }
    }
    return start - 1;
  }
}
//time: O(n), space: O(1)