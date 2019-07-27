/* Given a target integer T and an integer array A sorted in ascending order, Find the total number of occurrences of T in A.

Examples

A = {1, 2, 3, 4, 5}, T = 3, return 1
A = {1, 2, 2, 2, 3}, T = 2, return 3
A = {1, 2, 2, 2, 3}, T = 4, return 0
Corner Cases

What if A is null? We should return 0 in this case.  */

public class Solution {
  public int totalOccurrence(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int first = getFirst(nums, target);
    int second = getSecond(nums, target);
    if (first == -1 && second == -1) {
      return 0;
    }
    return second - first + 1;
  }

  private int getSecond(int[] nums, int target) {
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
    if (nums[end] == target) {
      return end;
    } else if (nums[start] == target) {
      return start;
    }
    return -1;
  }

  private int getFirst(int[] nums, int target) {
    int start = 0, end = nums.length - 1;

    while (start + 1 < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] == target) {
        end = mid;
      } else if (nums[mid] > target) {
        end = mid;
      } else {
        start = mid;
      }
    }
    if (nums[start] == target) {
      return start;
    } else if (nums[end] == target) {
      return end;
    } else {
      return -1;
    }
  }
}
//time: O(logn), space: O(1)