/* Search for a target number in a bitonic array, return the index of the target number if found in the array, or return -1.

A bitonic array is a combination of two sequence: the first sequence is a monotonically increasing one and the second sequence is a monotonically decreasing one.

Assumptions:

The array is not null.
Examples:

array = {1, 4, 7, 11, 6, 2, -3, -8}, target = 2, return 5. */

public class Solution {
  public int search(int[] nums, int target) {
      if (nums == null || nums.length == 0) {
          return -1;
      }
      if (nums.length == 1) {
          if (nums[0] == target) {
              return 0;
          } else {
              return -1;
          }
      }
      int index = getBitonic(nums);
      int leftIndex = findInLeft(nums, 0, index, target);
      int rightIndex = findInRight(nums, index, nums.length - 1, target);
      if (leftIndex == -1) {
          return rightIndex;
      } 
      return leftIndex;
  }
  
  private int findInLeft(int[] nums, int start, int end, int target) {
      while (start + 1 <  end) {
          int mid = start + (end - start) / 2;
          if (nums[mid] == target) {
              return mid;
          } else if (target < nums[mid]) {
              end = mid;
          } else {
              start = mid;
          }
      }
      if (nums[start] == target) {
          return start;
      } else if (nums[end] == target) {
          return end;
      } else {
          return -1;
      }
  }
  
  private int findInRight(int[] nums, int start, int end, int target) {
      while (start + 1 < end) {
          int mid = start + (end - start) / 2;
          if (nums[mid] == target) {
              return mid;
          } else if (target < nums[mid]) {
              start = mid;
          } else {
              end = mid;
          }
      }
      if (nums[end] == target) {
          return end;
      } else if (nums[start] == target) {
          return start;
      } else {
          return -1;
      }
  }
  
  //find the first element in decreasing array
  private int getBitonic(int[] nums) {
      int start = 0, end = nums.length - 1;
      while (start + 1 < end) {
          int mid = start + (end - start) / 2;
          if (nums[mid] < nums[mid - 1]) {
              end = mid;
          } else {
              start = mid;
          }
      }
      if (nums[start] > nums[start + 1]) {
          return start;
      }
      return end;
  }
}
//time: O(logn), space: O(1)