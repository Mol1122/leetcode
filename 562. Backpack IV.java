public class Solution {
    /**
     * @param nums: an integer array and all positive numbers, no duplicates
     * @param target: An integer
     * @return: An integer
     */
    public int backPackIV(int[] nums, int target) {
        int n = nums.length;
        int[] f = new int[target + 1];
        f[0] = 1;
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums[i]; j <= target; j++) {
                f[j] += f[j - nums[i]];
            }
        }
        return f[target];
    }
}

/* 算法：可以套用完全背包的模板， f[j]表示的是体积为j的方案数
** 时间复杂度:O(nv) 
** 空间复杂度：O(v) */