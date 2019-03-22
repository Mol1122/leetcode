public static int maxSubaray(int[] nums, int target) {
        int max = Integer.MIN_VALUE, sum = 0, min = 0;
        int ans = -1;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum - min);
            if (max <= target) {
                ans = Math.max(ans, max);
            }
            min = Math.min(min, sum);
        }
        return ans;
}