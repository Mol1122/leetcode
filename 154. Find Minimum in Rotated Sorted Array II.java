class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0, end = nums.length - 1;
        int target = nums[nums.length - 1];
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            // if mid equals to end, that means it's fine to remove end
            // the smallest element won't be removed
            if (nums[mid] == nums[end]) {
                end--;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] <= nums[end]) {
            return nums[start];
        } else {
            return nums[end];
        }
    }
}

/* 算法：这是用binary search的思想做出的题。如果mid的数等于end的数，那么end往前挪一位是可以的，只要mid小于最后一个数，那么永远可以往前找
** 时间复杂度：O(logn) */