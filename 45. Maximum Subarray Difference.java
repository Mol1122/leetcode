public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer indicate the value of maximum difference between two substrings
     */
    public int maxDiffSubArrays(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] left_max = new int[n];
        int[] left_min = new int[n];
        int[] right_max = new int[n];
        int[] right_min = new int[n];
        
        int[] copy = new int[n];
        for (int i = 0; i < n; i++) {
            copy[i] = -1 * nums[i];
        }
        
        int max = Integer.MIN_VALUE, sum = 0, min = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            max = Math.max(max, sum - min);
            min = Math.min(min, sum);
            left_max[i] = max;
        }
        
        max = Integer.MIN_VALUE;
        sum = 0;
        min = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum += nums[i];
            max = Math.max(max, sum - min);
            min = Math.min(min, sum);
            right_max[i] = max;
        }
        
        max = Integer.MIN_VALUE;
        sum = 0; 
        min = 0;
        for (int i = 0; i < n; i++) {
            sum += copy[i];
            max = Math.max(max, sum - min);
            min = Math.min(min, sum);
            left_min[i] = -1 * max;
        }
        
        max = Integer.MIN_VALUE;
        sum = 0;
        min = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum += copy[i];
            max = Math.max(max, sum - min);
            min = Math.min(min, sum);
            right_min[i] = -1 * max;
        }
        
        int diff = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            diff = Math.max(diff, Math.abs(left_min[i] - right_max[i + 1]));
            diff = Math.max(diff, Math.abs(left_max[i] - right_min[i + 1]));
        }
        return diff;
    }
}

/* 算法：利用prefixSum来做的，综合性比较强的一道题。因为是找两个数组，所有前后找max, 因为是绝对值，所以要从两端找min, max.这样这道题的基本思路出来了
** 时间复杂度：O(n) 
** 空间复杂度：O(n) */