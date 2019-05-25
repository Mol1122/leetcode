/* Given an array of positive integers representing a stock’s price on each day. On each day you can only make one operation: either buy or sell one unit of stock and you can make at most 1 transaction. Determine the maximum profit you can make.

Assumptions

The given array is not null and is length of at least 2.
Examples

{2, 3, 2, 1, 4, 5}, the maximum profit you can make is 5 - 1 = 4 */

public class Solution {
  public int maxProfit(int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    int min = nums[0], max = 0;
    for (int i = 1; i < nums.length; i++) {
        max = Math.max(max, nums[i] - min);
        min = Math.min(min, nums[i]);
    }
    return max;
  }
}
//time: O(n), space: O(1)
