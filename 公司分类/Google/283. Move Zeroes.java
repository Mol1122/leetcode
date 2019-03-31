public class Solution {
  public int[] moveZeroes(int[] nums) {
 /*     if (nums == null || nums.length == 0) {
          return nums;
      }
      //方法一: 循环两次, 先把所有非零移到前面, 剩余的再补零
      //适合于0少的情况
      int i = 0;
      for (int j = 0; j < nums.length; j++) {
          if (nums[j] != 0) {
              nums[i++] = nums[j];
          }
      }
      while (i < nums.length) {
          nums[i++] = 0;
      }
      return nums;  */ 
    
      //方法二：双指针做法，边循环边交换
      //适合于0多的情况, 并且能保持相对顺序
      if (nums == null || nums.length == 0) {
          return nums;
      }
      int left = 0, right = 0;
      while (right < nums.length) {
          if (nums[right] != 0) {
              int temp = nums[left];
              nums[left] = nums[right];
              nums[right] = temp;
              left++;
          }
          right++;
      }
      return nums;
    
      //WRONG:相对顺序会改变
/*      if (nums == null || nums.length == 0) {
          return nums;
      }
      int left = 0, right = nums.length - 1;
      while (left <= right) {
          if (nums[left] != 0) {
              left++;
          } else {
              int temp = nums[left];
              nums[left] = nums[right];
              nums[right] = temp;
              right--;
          }
      }
      return nums;  */
  }
}
