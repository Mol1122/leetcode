/* Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

Example:

Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4] */

class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null) {
            return;
        }
        for (int i = 1; i < nums.length; i++) {
            if ((i % 2 == 1) && nums[i] < nums[i - 1] ||
                (i % 2 == 0) && nums[i] > nums[i - 1]) {
                swap(nums, i, i - 1);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
//time: O(n), space: O(1)