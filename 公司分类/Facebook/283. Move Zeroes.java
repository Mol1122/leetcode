/* Given an array nums, write a function to move all 0's to the end of it while maintaining the 
relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0] */

class Solution {
    public void moveZeroes(int[] nums) {
        /*     if (nums == null || nums.length == 0) {
          return nums;
      }
      //方法三: 循环两次, 先把所有非零移到前面, 剩余的再补零
      //适合于0少的情况，并且尽量减少了write的次数
      int i = 0;
      for (int j = 0; j < nums.length; j++) {
          if (nums[j] != 0) {
              nums[i++] = nums[j];
          }
      }
      while (i < nums.length) {
          nums[i++] = 0;
      }
      return nums;  
      
      
      */ 
    
      //方法二：双指针做法，边循环边交换
      //适合于0多的情况, 并且能保持相对顺序
      if (nums == null || nums.length == 0) {
          return;
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
/* 如你所述，法三循环一次，最坏情况下需要n次交换（赋值3次），法2最坏情况下循环两次。算法的时间复杂度均为O(n)，区别只在前面的常数。
法1在0多的时候比较适用，法2在0少的时候合适；法1在数组大的时候效果更好。 */
/* 双指针，left指向最后一个非零数，right验证每一个新数 */