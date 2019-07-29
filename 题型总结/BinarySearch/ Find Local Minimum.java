/* Given an unsorted integer array, return any of the local minimum's index.

An element at index i is defined as local minimum when it is smaller than all its possible two neighbors a[i - 1] and a[i + 1]

(you can think a[-1] = +infinite, and a[a.length] = +infinite)

Assumptions:

The given array is not null or empty.
There are no duplicate elements in the array.
There is always one and only one result for each case. */

public class Solution {
  public int localMinimum(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int start = 0, end = nums.length - 1;
    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1]) {
        return mid;
      } else if (nums[mid] > nums[mid + 1]) {
        start = mid;
      } else {
        end = mid;
      }
    }
    if (nums[start] < nums[end]) {
      return start;
    }
    return end;
  }
}
//time: O(logn), space: O(1)