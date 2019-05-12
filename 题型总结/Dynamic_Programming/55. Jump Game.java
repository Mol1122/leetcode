public class Solution {
  public boolean canJump(int[] nums) {
      if (nums == null || nums.length == 0) {
          return true;
      }
      boolean[] f = new boolean[nums.length];
      f[0] = true;
    
      for (int i = 1; i < nums.length; i++) {
          for (int j = 0; j < i; j++) {
              if (f[j] && j + nums[j] >= i) {
                  f[i] = true;
                  break;
              }
          }
      }
      return f[nums.length - 1];
  }
}
//canJump[i] = 是否能从index 0跳到index i
//time: O(n^2), space: O(n)
