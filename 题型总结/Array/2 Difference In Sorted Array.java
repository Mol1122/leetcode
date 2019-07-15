/* Given a sorted array A, find a pair (i, j) such that A[j] - A[i] is identical to a target number(i != j).

If there does not exist such pair, return a zero length array.

Assumptions:

The given array is not null and has length of at least 2.
Examples:

A = {1, 2, 3, 6, 9}, target = 2, return {0, 2} since A[2] - A[0] == 2.
A = {1, 2, 3, 6, 9}, target = -2, return {2, 0} since A[0] - A[2] == 2. */

public class Solution {
  public int[] twoDiff(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return new int[0];
    }
    int i = 0, j = 0;
    while (i < nums.length && j < nums.length) {
      int diff = nums[j] - nums[i];
      if (diff > target) {
        i++;
      } else if (diff < target) {
        j++;
      } else {
        if (i != j) {
          return new int[]{i, j};
        } else {
          j++;
        }
      }
    }
    return new int[]{};
  }
}
//time: O(n), space: O(1)