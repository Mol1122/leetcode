public int longestSubstringContainingOne(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k < 0) {
        return 0;
    }
    int slow = 0, fast = 0;
    int max = 0;

    while (fast < nums.length) {
        if (nums[fast] == 1) {
            fast++;
            max = Math.max(max, fast - slow);
        } else {
            if (k > 0) {
                fast++;
                max = Math.max(max, fast - slow);
                k--;
            } else {
                if (nums[slow] == 0) {
                    k++;
                }
                slow++;
            }
        }
    }
    return max;
}