class Solution {
    int ans = 0;
    public int findTargetSumWays(int[] nums, int S) {     
        dfs(nums, S, 0, 0);
        return ans;
    }
    
    private void dfs(int[] nums, int S, int index, int currSum) {
        if (index == nums.length) {
            if (currSum == S) {
                ans = ans + 1;
            }
           return;
        }

        dfs(nums, S, index + 1, currSum + nums[index]);
        dfs(nums, S, index + 1, currSum - nums[index]);    
    }
}