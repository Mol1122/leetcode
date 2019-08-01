/* Given an array of integers, sort the elements in the array in ascending order. The merge sort algorithm should be used to solve this problem.

Examples

{1} is sorted to {1}
{1, 2, 3} is sorted to {1, 2, 3}
{3, 2, 1} is sorted to {1, 2, 3}
{4, 2, -3, 6, 1} is sorted to {-3, 1, 2, 4, 6}
Corner Cases

What if the given array is null? In this case, we do not need to do anything.
What if the given array is of length zero? In this case, we do not need to do anything. */

public class Solution {
  public int[] mergeSort(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return nums;
    }
    int[] temp = new int[nums.length];
    mergeSortHelper(nums, 0, nums.length - 1, temp);
    return nums;
  }

  private void mergeSortHelper(int[] nums, int start, int end, int[] temp) {
    if (start >= end) {
      return;
    }
    mergeSortHelper(nums, start, (start + end) / 2, temp);
    mergeSortHelper(nums, (start + end) / 2 + 1, end, temp);
    merge(nums, start, end, temp);
  }

  private void merge(int[] nums, int start, int end, int[] temp) {
    int middle = (start + end) / 2;
    int left = start;
    int right = middle + 1;
    int index = start;

    while (left <= middle && right <= end) {
      if (nums[left] < nums[right]) {
        temp[index++] = nums[left++];
      } else {
        temp[index++] = nums[right++];
      }
    }
    while (left <= middle) {
      temp[index++] = nums[left++];
    }
    while (right <= end) {
      temp[index++] = nums[right++];
    }
    for (int i = start; i <= end; i++) {
      nums[i] = temp[i];
    }
  }
}
//time: O(nlogn), space: O(n)