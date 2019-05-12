public class Solution {
  public int longest(int[] nums) {
      if (nums == null || nums.length == 0) {
          return 0;
      }
      if (nums.length == 1) {
          return 1;
      }
      int[] f = new int[2];
      f[0] = 1;
      int max = 0;
      
      for (int i = 1; i < nums.length; i++) {
          if (nums[i] > nums[i - 1]) {
              f[i%2] = f[(i - 1)%2] + 1;
          } else {
              f[i%2] = 1;
          }
          max = Math.max(max, f[i%2]);
      }
      return max;
  }
}
//f[i] = the longest subarray ending at index i
//time: O(n), space: 可以优化到O(1)

//这种方法更好
public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int answer = 1;
        
        // from left to right
        int length = 1; // just A[0] itself
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                length++;
            } else {
                length = 1;
            }
            answer = Math.max(answer, length);
        }
        return answer;
    }