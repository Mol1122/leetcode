public class Solution {
  public int[] mergeSort(int[] nums) {
      if (nums == null || nums.length == 0) {
          return nums;
      }
      int[] temp = new int[nums.length];
      mergeSortHelper(nums, 0, nums.length - 1, temp);
      return nums;
  }
  
  private void mergeSortHelper(int[] nums, int start, int end, int[] temp) {
      if (start >= end) {
          return;
      }
      mergeSortHelper(nums, start, (start + end) / 2, temp);
      mergeSortHelper(nums, (start + end) / 2 + 1, end, temp);
      merge(nums, start, end, temp);
  }
  
  private void merge(int[] nums, int start, int end, int[] temp) {
      int middle = (start + end) / 2;
      int leftIndex = start;
      int rightIndex = middle + 1;
      int index = start;
    
      while (leftIndex <= middle && rightIndex <= end) {
          if (nums[leftIndex] <= nums[rightIndex]) {
              temp[index++] = nums[leftIndex++];
          } else {
              temp[index++] = nums[rightIndex++];
          }
      }
      while (leftIndex <= middle) {
          temp[index++] = nums[leftIndex++];
      }
      while (rightIndex <= end) {
          temp[index++] = nums[rightIndex++];
      }
      for (int i = start; i <= end; i++) {
          nums[i] = temp[i];
      }
  }
}
/* 时间复杂度：O(n) + O(nlogn) = O(nlogn) 
** 空间复杂度：O(logn)*/
