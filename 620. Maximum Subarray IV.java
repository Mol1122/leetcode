public class Solution {
    /**
     * @param nums: an array of integer
     * @param k: an integer
     * @return: the largest sum
     */
    public int maxSubarray4(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < k; i++) {
            result += nums[i];
        }
        int min_prefix = 0;
        int n = nums.length;
        int[] sum = new int[n + 1];
        
        sum[0] = 0;
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
            if (i >= k && sum[i] - min_prefix > result) {
                result = Math.max(result, sum[i] - min_prefix);
            }
            if (i >= k) {
                min_prefix = Math.min(min_prefix, sum[i - k + 1]);
            }
        }
        return result;
    }
}

/* 算法：前缀和数组
** 难点：26行 sum[i - k + 1], 因为是第几个数，所以要+1. 面试的时候可以带特殊值进去算 */