/* Given a target integer T and an integer array A sorted in ascending order, find the index of the smallest element in A that is larger than T or return -1 if there is no such index.

Assumptions

There can be duplicate elements in the array.

Examples

A = {1, 2, 3}, T = 1, return 1

A = {1, 2, 3}, T = 3, return -1

A = {1, 2, 2, 2, 3}, T = 1, return 1


Corner Cases

What if A is null or A of zero length? We should return -1 in this case. */

public class Solution {
  public int smallestElementLargerThanTarget(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int start = 0, end = nums.length - 1;

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] == target) {
        start = mid;
      } else if (nums[mid] < target) {
        start = mid;
      } else {
        end = mid;
      }
    }
    if (nums[start] > target) {
      return start;
    } else if (nums[end] > target) {
      return end;
    }
    return -1;

  }
}
//time: O(logn), space: O(1)