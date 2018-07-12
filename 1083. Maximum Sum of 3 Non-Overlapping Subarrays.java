public class Solution {
    /**
     * @param nums: an array
     * @param k: an integer
     * @return: three non-overlapping subarrays with maximum sum
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] posLeft = new int[n], posRight = new int[n], sum = new int[n + 1], ans = new int[3];
        int maxSum = 0;
        
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        //left
        for (int i = k, total = sum[k] - sum[0]; i < n; i++) {
            if (sum[i + 1] - sum[i + 1 - k] > total) {
                posLeft[i] = i + 1 - k;
                total = sum[i + 1] - sum[i + 1 - k];
            } else {
                posLeft[i] = posLeft[i - 1];
            }
        }
        //right
        posRight[n - k] = n - k;
        for (int i = n - k, total = sum[n] - sum[n- k]; i >= 0; i--) {
            if (sum[i + k] - sum[i] >= total) { //!因为index要更小的
                posRight[i] = i;
                total = sum[i + k] - sum[i];
            } else {
                posRight[i] = posRight[i + 1];
            }
        }
        //middle
        for (int i = k; i <= n - 2 * k; i++) {
            int l = posLeft[i - 1], r = posRight[i + k];
            int temp = (sum[i + k] - sum[i]) + (sum[l + k] - sum[l]) + (sum[r + k] - sum[r]);
            if (temp > maxSum) {
                ans[0] = l;
                ans[1] = i;
                ans[2] = r;
                maxSum = temp;
            }
        }
        return ans;
    }
}

/* 算法：区间类动态规划
** 时间复杂度: O(n) 
** 难点：sum比nums本身的index大1 */