class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return - 1;
        }
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[start] < nums[mid]) { //pivot on the right, and the first part is increasing
                if (target >= nums[start] && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
}

/* 算法：二分。因为并不知道pivot在哪里，但是可以肯定的是，pivot肯定在mid的左边或者右边。这就意味着，mid的左边或者右边一定是升序的.
时间复杂度：O(logn)
*/