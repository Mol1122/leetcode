public class Solution {
    /**
     * @param nums: an array of integers
     * @param s: An integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int ans = Integer.MAX_VALUE;
        int j = 0;
        int sum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            while (j < nums.length && sum < s) {
                sum += nums[j];
                j++;
            }
            if (sum >= s) {
                ans = Math.min(ans, j - i);
            }
            sum -= nums[i];
        }
        if (ans == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }
}

/* 算法：同向双指针
** 时间复杂度：O(n) */