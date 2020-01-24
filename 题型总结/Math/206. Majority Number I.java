/* Given an integer array of length L, find the number that occurs more than 0.5 * L times.

Assumptions

The given array is not null or empty
It is guaranteed there exists such a majority number
Examples

A = {1, 2, 1, 2, 1}, return 1 */

public class Solution {
  public int majority(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int candidate = nums[0];
    int count = 1;

    for (int i = 1; i < nums.length; i++) {
      if (count == 0) {
        candidate = nums[i];
        count = 1;
      } else if (candidate == nums[i]) {
        count++;
      } else {
        count--;
      }
    }
    return candidate;
  }
}
//算法：Voting algorithm
//time: O(n), space: O(1)