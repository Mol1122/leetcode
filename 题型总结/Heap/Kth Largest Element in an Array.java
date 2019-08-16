/* Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length. */

public class Solution {
  public int findKthLargest(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    return quickSelect(nums, 0, nums.length - 1, nums.length - k);
  }

  private int quickSelect(int[] nums, int start, int end, int k) {
    if (start >= end) {
      return nums[start];
    }
    int left = start, right = end;
    int pivot = nums[(start + end) / 2];

    while (left <= right) {
      while (left <= right && nums[left] < pivot) {
        left++;
      }
      while (left <= right && nums[right] > pivot) {
        right--;
      }
      if (left <= right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
        left++;
        right--;
      }
    }
    if (k <= right) {
      return quickSelect(nums, start, right, k);
    } else if (k >= left) {
      return quickSelect(nums, left, end, k);
    } else {
      return nums[k];
    }
  }
}
//time: O(n), space: O(n)