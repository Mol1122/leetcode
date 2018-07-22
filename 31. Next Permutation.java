class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int i = nums.length - 1;
        while (i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }
        if (i != 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i - 1]) {
                j--;
            }
            swapItem(nums, i - 1, j);
        }
        swapList(nums, i, nums.length - 1);
    }
    
    private void swapList(int[] nums, int start, int end) {
        while (start < end) {
            swapItem(nums, start, end);
            start++;
            end--;
        }
    }
    
    private void swapItem(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/* 算法：这是一个全排列问题。
** 难点：最后swapList的时候是从i到最后，而不一定是从0开始 */　