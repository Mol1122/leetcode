public class Solution {
  public double largestProduct(double[] nums) {
      if (nums == null || nums.length == 0) {
          return 0;
      }
      int n = nums.length;
      double[] min = new double[n];
      double[] max = new double[n];
      double product = nums[0];
      min[0] = max[0] = nums[0];
      
      for (int i = 1; i < n; i++) {
          min[i] = max[i] = nums[i];
          if (nums[i] > 0) {
              max[i] = Math.max(max[i], max[i - 1] * nums[i]);
              min[i] = Math.min(min[i], min[i - 1] * nums[i]);
          } else {
              max[i] = Math.max(max[i], min[i - 1] * nums[i]);
              min[i] = Math.min(min[i], max[i - 1] * nums[i]);
          }
          product = Math.max(product, max[i]);
      }
      return product;
  }
}
//time: O(n), space: O(n)