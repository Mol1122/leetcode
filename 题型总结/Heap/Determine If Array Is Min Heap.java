public class Solution {
  public boolean isMinHeap(int[] nums) {
      if (nums == null || nums.length == 0) {
          return true;
      }
      int n = nums.length;
      for (int i = 0; i <= (n - 2) / 2; i++) {
          if (nums[2 * i + 1] < nums[i]) {
              return false;
          }
          if (2 * i + 2 < n && nums[2 * i + 2] < nums[i]) {
              return false;
          }
      }
      return true;
  }
}

/* 时间复杂度：O(n)
** 空间复杂度：O(1) */
