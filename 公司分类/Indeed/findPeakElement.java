public static int findPeakElement(int[] nums) {
        int start = 1, end = nums.length - 2;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= nums[mid - 1]) {
                end = mid;
            } else if (nums[mid] >= nums[mid + 1]) {
                start = mid;
            } else {
                return nums[mid];
            }
        }
        if (nums[start] < nums[end] && nums[start] < nums[start - 1]) {
            return nums[start];
        }
        if (nums[end] < nums[start] && nums[end] < nums[end + 1]) {
            return nums[end];
        }
        return -1;
}