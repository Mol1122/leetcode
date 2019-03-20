public class Solution {
  public int[] quickSort(int[] nums) {
      if (nums == null || nums.length == 0) {
          return nums;
      }
      quickSortHelper(nums, 0, nums.length - 1);
      return nums;
  }
  
  private void quickSortHelper(int[] nums, int start, int end) {
      if (start >= end) {
          return;
      }
      int left = start, right = end;
      int pivot = nums[(start + end) / 2];
      
      while (left <= right) {
          while (left <= right && nums[left] < pivot) {
              left++;
          }
          while (left <= right && nums[right] > pivot) {
              right--;
          }
          if (left <= right) {
              int temp = nums[left];
              nums[left] = nums[right];
              nums[right] = temp;
              left++;
              right--;
          }
      }
      quickSortHelper(nums, start, right);
      quickSortHelper(nums, left, end);
  }
}

/* 时间复杂度：O(nlogn), 每层一个for循环，O(n), 一共logn层
** 空间复杂度：O(1)*/