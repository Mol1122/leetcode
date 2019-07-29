/* Given an integer array A, A is sorted in ascending order first then shifted by an arbitrary number of positions, For Example, A = {3, 4, 5, 1, 2} (shifted left by 2 positions). Find the index of the smallest number.

Assumptions

There are no duplicate elements in the array
Examples

A = {3, 4, 5, 1, 2}, return 3
A = {1, 2, 3, 4, 5}, return 0
Corner Cases

What if A is null or A is of zero length? We should return -1 in this case. */

public class Solution {
  public int shiftPosition(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int start = 0, end = nums.length - 1;
    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] < nums[end]) { //right side is increasing
        end = mid;
      } else {
        start = mid;
      }
    }
    if (nums[start] < nums[end]) {
      return start;
    }
    return end;
  }
}
//time: O(logn), space: O(1)