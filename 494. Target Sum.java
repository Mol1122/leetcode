class Solution {
//     int ans = 0;
//     public int findTargetSumWays(int[] nums, int S) {     
//         dfs(nums, S, 0, 0);
//         return ans;
//     }
    
//     private void dfs(int[] nums, int S, int index, int currSum) {
//         if (index == nums.length) {
//             if (currSum == S) {
//                 ans = ans + 1;
//             }
//            return;
//         }

//         dfs(nums, S, index + 1, currSum + nums[index]);
//         dfs(nums, S, index + 1, currSum - nums[index]);    
//     }
    
    //convert to subset problem, and subset problem is essentially a 0-1 backpack
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if ((S + sum) % 2 != 0) {
            return 0;
        }
        if (sum < S) {
            return 0;
        }
        sum = (S + sum) / 2;
        
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                if (j - nums[i] >= 0) {
                    dp[j] += dp[j - nums[i]];
                }
                
            }
        }
        return dp[sum];
    }
}

/* 算法：dfs
** 难点：ans要是class变量，否则值不会改变

    算法：动态规划, convert the problem to find a subset of nums that = (S + sum) / 2
    sum(P) - sum(N) = target
sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
    2 * sum(P) = target + sum(nums)
    
    难点：32-34 如果所有数加起来肯定不能凑成target那么没有走下去的必要了
*/