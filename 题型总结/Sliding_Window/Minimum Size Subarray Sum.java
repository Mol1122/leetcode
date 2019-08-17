/* Given an array of n positive integers and a positive integer s, find the minimal length of 
a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint. */

public class Solution {
  public int minSubArrayLen(int target, int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    int min = Integer.MAX_VALUE;
    int sum = 0;
    
    int j = 0;
    for (int i = 0; i < nums.length; i++) {
        while (j < nums.length && sum < target) {
            sum += nums[j];
            j++;
        }
        if (sum >= target) {
            min = Math.min(min, j - i);
        }
        sum -= nums[i];
    }
    return min == Integer.MAX_VALUE ? 0 : min;
  }
}
//time: O(n), space: O(1)