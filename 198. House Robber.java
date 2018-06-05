class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] result = new int[n + 1];
        result[0] = 0;
        result[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            result[i] = Math.max(result[i - 1], result[i - 2] + nums[i - 1]);
        }
        return Math.max(result[nums.length - 1], result[nums.length]);
    }
}

/* 算法：前缀和数组 */