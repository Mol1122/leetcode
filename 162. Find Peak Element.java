class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[mid - 1]) {
                end = mid;
            } else if (nums[mid] > nums[mid - 1]) {
                start = mid;
            } else {
                return mid;
            }
        }
        if (nums[start] > nums[end]) {
            return start;
        }
        return end;
    }
}

/* 算法：二分法half, half.当mid是decreasing的时候，peak一定在左边，反之在右边
** 时间复杂度：O(logn) */