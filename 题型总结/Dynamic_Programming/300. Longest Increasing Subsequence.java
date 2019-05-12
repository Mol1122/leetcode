public class Solution {
  public int longest(int[] nums) {
      if (nums == null || nums.length == 0) {
          return 0;
      }
      int[] f = new int[nums.length];
      int max = Integer.MIN_VALUE;
    
      for (int i = 0; i < nums.length; i++) {
          f[i] = 1;
          for (int j = 0; j < i; j++) {
              if (nums[j] < nums[i]) {
                  f[i] = Math.max(f[i], f[j] + 1);
              }
          }
          max = Math.max(max, f[i]);
      }
      return max;
  }
}
//f[i] = max length of increasing subsequence from index 0 to index i, in
//not necessarily include index i
//time: O(n^2), space: O(n)