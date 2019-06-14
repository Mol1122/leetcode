/* Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police. */

public class Solution {
  public int rob(int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    int n = nums.length;
    int[] f1 = new int[n + 1]; //不打劫最后一栋房子
    int[] f2 = new int[n + 1]; //不打结第一栋房子
    f1[0] = 0;
    f1[1] = nums[0];
    for (int i = 2; i <= n; i++) {
        if (i == n) {
            f1[i] = f1[i - 1];
            continue;
        }
        f1[i] = Math.max(f1[i - 1], f1[i - 2] + nums[i - 1]);
    }
    
    f2[0] = f2[1] = 0;
    for (int i = 2; i <= n; i++) {
        f2[i] = Math.max(f2[i - 1], f2[i - 2] + nums[i - 1]);
    }
    return Math.max(f1[n], f2[n]);
  }
}
//time: O(n), space: O(n) -> O(1)