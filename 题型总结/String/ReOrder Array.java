public class Solution {
  public int[] reorder(int[] nums) {
      if (nums == null || nums.length == 0) {
          return nums;
      }
      if (nums.length % 2 == 0) {
          helper(nums, 0, nums.length - 1);
      } else {
          helper(nums, 0, nums.length - 2);
      }
      return nums;
  }
  
  private void helper(int[] nums, int left, int right) {
      int length = right - left + 1;
      if (length <= 2) {
          return;
      }
      int mid = left + (length / 2);
      int lmid = left + (length / 4);
      int rmid = left + length * 3 / 4;
    
      reverse(nums, lmid, mid - 1);
      reverse(nums, mid, rmid - 1);
      reverse(nums, lmid, rmid - 1);
    
      //难点：不能直接用mid index是因为reverse后，长度可能不是原来的长度了
      helper(nums, left, left + (lmid - left) * 2 - 1);
      helper(nums, left + (lmid - left) * 2, right);
  }
  
  private void reverse(int[] nums, int start, int end) {
      while (start < end) {
          int temp = nums[start];
          nums[start] = nums[end];
          nums[end] = temp;
          start++;
          end--;
      }
  }
}

/* 时间复杂度：O(nlogn)
** 空间复杂福：O(1) */
