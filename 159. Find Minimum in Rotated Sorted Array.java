public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;
        int target = nums[nums.length - 1];
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] < target) {
            return nums[start];
        } else {
            return nums[end];
        }
    }
}

/* 算法：把问题变成找出从左网友第一个比target小的数
** 时间复杂度：O(logn) */