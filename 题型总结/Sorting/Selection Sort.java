public class Solution {
  public int[] solve(int[] nums) {
      if (nums == null || nums.length <= 1) {
          return nums;
      }
      for (int i = 0; i < nums.length - 1; i++) {
          int min = nums[i];
          int index = i;
          for (int j = i + 1; j < nums.length; j++) {
              if (nums[j] < min) {
                  min = nums[j];
                  index = j;
              }
          }
          int temp = nums[i];
          nums[i] = nums[index];
          nums[index] = temp;
      }
      return nums;
  }
}

/* 时间复杂度：O(n^2)
** 空间复杂度: O(1) */
