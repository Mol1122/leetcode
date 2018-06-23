class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;
        int target = nums[nums.length - 1];
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
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

/* 算法：利用二分法，第一个比最后一个数小的就是最小值
** 时间复杂度：O(logn) */