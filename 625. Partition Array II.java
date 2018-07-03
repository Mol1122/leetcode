public class Solution {
    /*
     * @param nums: an integer array
     * @param low: An integer
     * @param high: An integer
     * @return: 
     */
    public void partition2(int[] nums, int low, int high) {
        if (nums == null || nums.length < 1) {
            return;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            while (left <= right && nums[left] < low) {
                left++;
            }
            while (left <= right && nums[right] >= low) {
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
        
        right = nums.length - 1;
        while (left <= right) {
            while (left <= right && nums[left] < high) {
                left++;
            }
            while (left <= right && nums[left] >= high) {
                right--;
            } 
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
    }
}

/* 算法：按照low, 和highpartition最容易的方法就是partition两次，这样达到了降维的目的
** 时间复杂度：O(n)
** 空间复杂度：O(1) */