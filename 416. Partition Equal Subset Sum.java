class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum /= 2;
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        
        dp[0][0] = true;
        //initialize
      
        for (int i = 1; i <= nums.length; i++) {
            dp[i][0] = true;
        }
        for (int j = 1; j <= sum; j++) {
            dp[0][j] = false;
        }
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][sum];
    }
}


/* 算法：这是0-1背包问题。
        state:dp[i][j] = 在前i个数中能不能找出一些数，使得和为j 
        function: dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]]
        
    时间复杂度：O(nm)
    难点：nums[i - 1]注意开始的位置
        */