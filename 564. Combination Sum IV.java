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
        // dp[0] = 1;
        // for (int i = 1; i <= target; i++) {
        //     for (int j = 1; j <= n; j++) {;
        //         if (nums[j - 1] <= i) {
        //             dp[i] += dp[i - nums[j - 1]];
        //         }
        //     }
        // }
        // return dp[target];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[j] <= i) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}

/* 算法: dp[i]表示的是和为i的组合的个数，外层的for循环表示的是遍历所有可能的和，就像0-1背包的遍历所有的物品。
**       内层for循环表示的是所有可能的放入的数,相当于所有可能体积，就像0-1背包要遍历所有可能的体积 */