/* Determine if the given integer array is min heap. */

public class Solution {
  public boolean isMinHeap(int[] nums) {
    if (nums == null || nums.length == 0) {
      return true;
    }
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      if (2 * i + 1 < n && nums[2 * i + 1] < nums[i]) {
        return false;
      }
      if (2 * i + 2 < n && nums[2 * i + 2] < nums[i]) {
        return false;
      }
    }
    return true;
  }
}
//time: O(n), space: O(1)

