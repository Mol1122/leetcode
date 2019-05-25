public class Solution {
  public int longestBitonic(int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    int n = nums.length;
    int[] left = new int[n];
    int[] right = new int[n];

    left[0] = 1;
    for (int i = 1; i < n; i++) {
        left[i] = 1;
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                left[i] = Math.max(left[i], left[j] + 1);
            }
        }
    }
    right[n - 1] = 1;
    for (int i = n - 2; i >= 0; i--) {
        right[i] = 1;
        for (int j = n - 1; j > i; j--) {
            if (nums[j] < nums[i]) {
                right[i] = Math.max(right[i], right[j] + 1);
            }
        }
    }
    int max = 1;
    for (int i = 0; i < n; i++) {
        max = Math.max(max, left[i] + right[i] - 1);
    }
    return max;
  }
}
//time: O(n^2), space: O(n)