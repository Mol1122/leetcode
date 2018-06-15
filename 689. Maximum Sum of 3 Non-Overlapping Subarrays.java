class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length, maxSum = 0;
        int[] sum = new int[n+1], posLeft = new int[n], posRight = new int[n], ans = new int[3];
        
        for (int i = 0; i < n; i++) {
            sum[i+1] = sum[i] + nums[i];
        }
        //left
        for (int i = k, total = sum[k] - sum[0]; i < n; i++) {
            if (sum[i+1] - sum[i+1-k] > total) {
                posLeft[i] = i+1-k;
                total = sum[i+1] - sum[i+1-k];
            } else {
                posLeft[i] = posLeft[i-1];
            }
        }
        //right
        posRight[n-k] = n-k; //易漏， 必须要有，因为array initialize to 0
        for (int i = n-1-k, total = sum[n] - sum[n-k]; i >= 0; i--) {
            if (sum[i+k] - sum[i] >= total) {
                posRight[i] = i;
                total = sum[i+k] - sum[i];
            } else {
                posRight[i] = posRight[i+1];
            }
        }
        //middle
        for (int i = k; i <= n-2*k; i++) {
            int l = posLeft[i-1], r = posRight[i+k];
            int temp = (sum[i+k] - sum[i]) + (sum[l+k] - sum[l]) + (sum[r+k] - sum[r]);
            if (temp > maxSum) {
                maxSum = temp;
                ans[0] = l;
                ans[1] = i;
                ans[2] = r;
            }
        }
        return ans;
    }
}

/* 算法：dynamic programming
** 时间复杂度：O(n)
** 空间复杂度: O(3n) */