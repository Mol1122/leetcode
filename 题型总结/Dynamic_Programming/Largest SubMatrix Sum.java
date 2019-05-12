public class Solution {
  public int largest(int[][] matrix) {
      if (matrix == null || matrix.length == 0) {
          return 0;
      }
      int n = matrix.length;
      int m = matrix[0].length;
      int max = Integer.MIN_VALUE;
    
      for (int i = 0; i < n; i++) {
          int[] curr = new int[m];
          for (int j = i; j < n; j++) {
              add(curr, matrix[j]);
              int result = getMaxSubarray(curr);
              max = Math.max(max, result);
          }
      }
      return max;
  }
  
  private int getMaxSubarray(int[] nums) {
      int[] f = new int[nums.length];
      f[0] = nums[0];
      int max = f[0];
      
      for (int i = 1; i < nums.length; i++) {
          f[i] = nums[i];
          if (f[i - 1] > 0) {
              f[i] += f[i - 1];
          }
          max = Math.max(max, f[i]);
      }
      return max;
  }
  
  private void add(int[] curr, int[] array) {
      for (int i = 0; i < array.length; i++) {
          curr[i] += array[i];
      }
  }
}
//time: O(n^3), space: O(n^2)
