public class Solution {
  public int minJump(int[] nums) {
      if (nums == null || nums.length == 0) {
          return -1;
      }
      int[] f = new int[nums.length];
      f[0] = 0;
      
      for (int i = 1; i < nums.length; i++) {
          f[i] = -1; //cannot jump to index i
          for (int j = 0; j < i; j++) {
              if (f[j] != -1 && nums[j] + j >= i) {
                  if (f[i] == -1 || f[j] + 1 < f[i]) { //易漏，check是否可以被更新
                      f[i] = f[j] + 1;
                  }
              }
          }
      }
      return f[nums.length - 1];
  }
}
//f[i] = min # jumps need to take from index 0 to index i
//time: O(n^2), space: O(n)