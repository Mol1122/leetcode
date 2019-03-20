public class Solution {
  public int missing(int[] nums) {
      if (nums.length == 0) {
          return 1;
      }
      int start = 0, end = nums.length - 1;
      while (start + 1 < end) {
          int mid = start + (end - start) / 2;
          if (nums[mid] != mid + 1) {
              end = mid;
          } else if (nums[mid] == mid + 1) {
              start = mid;
          }
      }
      if (nums[start] != start + 1) {
          return start + 1;
      } else if (nums[end] != end + 1) {
          return end + 1;
      } else {
          return nums.length + 1;
      }
  }
}
