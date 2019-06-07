/* Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:

Given [3, 1, 5, 8] */

public class Solution {
  public int maxCoins(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int[] helper = new int[nums.length + 2];
    helper[0] = 1;
    for (int i = 1; i <= nums.length; i++) {
      helper[i] = nums[i - 1];
    }
    helper[helper.length - 1] = 1;
    int n = helper.length;
    int[][] f = new int[n][n];

    for (int i = n - 1; i >= 0; i--) {
      for (int j = i + 2; j < n; j++) {
        for (int k = i + 1; k < j; k++) {
          f[i][j] = Math.max(f[i][j], helper[i] * helper[k] * helper[j] + f[i][k] + f[k][j]);
        }
      }
    }
    return f[0][n - 1];
  }
}
//time: O(n^3), spasce: O(n^2)