/* Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11]. */

class Solution {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int n = nums.length;
        int m = sum / 2;
        boolean[][] f = new boolean[n + 1][m + 1];
        
        f[0][0] = true;
        for (int i = 1; i <= m; i++) {
            f[0][i] = false;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= nums[i - 1]) {
                    f[i][j] |= f[i - 1][j - nums[i - 1]];
                }
            }
        }
        return f[n][m];
    }
}


/* 算法：这是0-1背包问题。
        state:dp[i][j] = 在前i个数中能不能找出一些数，使得和为j 
        function: dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]]
        
    时间复杂度：O(nm)
    难点：nums[i - 1]注意开始的位置
        */