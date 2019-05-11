public class Solution {
  public int minJump(int[] nums) {
      if (nums == null || nums.length == 0) {
          return 0;
      }
      int n = nums.length;
      int[] f = new int[n + 1];
      f[0] = 0;
    
      for (int i = 1; i <= n; i++) {
          f[i] = -1;
          for (int j = 0; j < i; j++) {
              if (f[j] != -1 && j + nums[j] >= i) {
                  if (f[i] == -1 || f[j] + 1 < f[i]) {
                      f[i] = f[j] + 1;
                  }
              }
          }
      }
      return f[n];
  }
}
//f[i] = min jump reaching index i
//time: O(n), space: O(n)