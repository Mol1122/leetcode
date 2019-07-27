/* Given a target integer T and an integer array A sorted in ascending order, find the index i in A such that A[i] is closest to T.

Assumptions

There can be duplicate elements in the array, and we can return any of the indices with same value. */

public class Solution {
  public int closest(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int start = 0, end = nums.length - 1;
    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] < target) {
        start = mid;
      } else {
        end = mid;
      }
    }
    if (Math.abs(target - nums[start]) < Math.abs(target - nums[end])) {
      return start;
    }
    return end;
  }
}
//time: O(logn), space: O(1)