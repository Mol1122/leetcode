public class Solution {
  public int[] productExceptSelf(int[] nums) {
    if (nums == null || nums.length == 0) {
        return new int[0];
    }
    int[] products = new int[nums.length + 1];
    products[0] = 1;
    for (int i = 0; i < nums.length; i++) {
        products[i + 1] = products[i] * nums[i];
    }
    int[] res = new int[nums.length];
    int right = 1;
    for (int i = nums.length - 1; i >= 0; i--) {
        res[i] = products[i] * right;
        right *= nums[i];
    }
    return res;
  }
}
//time: O(n), space: O(n)