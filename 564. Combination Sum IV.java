public class Solution {
    /**
     * @param nums: an integer array and all positive numbers, no duplicates
     * @param target: An integer
     * @return: An integer
     */
    public int backPackVI(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 1; j <= n; j++) {;
                if (nums[j - 1] <= i) {
                    dp[i] += dp[i - nums[j - 1]];
                }
            }
        }
        return dp[target];
    }
}