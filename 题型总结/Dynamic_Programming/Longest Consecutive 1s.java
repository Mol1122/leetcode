public class Solution {
  public int longest(int[] nums) {
      if (nums == null || nums.length == 0) {
          return 0;
      }
      int[] f = new int[nums.length];
      f[0] = nums[0];
      
      int max = f[0];
      for (int i = 1; i < nums.length; i++) {
          if (nums[i] == 0) {
              f[i] = 0;
          } else {
              f[i] = f[i - 1] + 1;
          }
          max = Math.max(max, f[i]);
      }
      return max;
  }
}
//f[i] = max length of subarray that contains 1s, must include index i (因为subarray是连续的)
//time: O(n), space: O(n)
