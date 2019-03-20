class Solution {
    public int localMinimum(int[] nums) {
      if (nums == null || nums.length == 0) {
          return 0;
      }
      int start = 0, end = nums.length - 1;
      while (start + 1 < end) {
          int mid = start + (end - start) / 2;
          if (nums[mid - 1] < nums[mid]) {
              end = mid;
          } else {
              start = mid;
          }
      }
      if (nums[start] < nums[end]) {
          return start;
      } else {
          return end;
      }
  }
}

/* 算法：binary search
** 时间复杂度：O(logn) */