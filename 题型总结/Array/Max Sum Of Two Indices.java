/* Given an array with integers, find two indices i and j  (j>=i),  such that the value of A[i]+A[j]+ (j - i) is maximized.

Return (i, j).

Assumptions:

The given array is not null and it has length of > 0.
Examples:

array = {1, 5, 3}, the max sum is array[1] + array[1] + (1 - 1) = 10, return {1, 1} */

public class Solution {
  public int[] maxSum(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new int[0];
    }
    int currI = 0, currJ = 0;
    int maxI = 0, maxJ = 0;
    int max = nums[currI] + nums[currJ] - currI + currJ;

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] + i > nums[currJ] + currJ) { //update j
        currJ = i;
      }
      if (nums[i] - i > nums[currI] - currI) { //update i
        currI = i;
        currJ = i;
      }
      int curr = nums[currI] + nums[currJ] - currI + currJ;
      if (curr > max) {
        max = curr;
        maxI = currI;
        maxJ = currJ;
      }
    }
    return new int[]{maxI, maxJ};
  }
}
//time: O(n), space: O(1)
