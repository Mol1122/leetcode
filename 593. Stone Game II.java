public class Solution {
    /**
     * @param A: An integer array
     * @return: An integer
     */
     
    int[] arr;
    int[] preSum;
    int[][] dp;
    public int stoneGame2(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        arr = new int[2 * n];
        preSum = new int[2 * n + 1];
        dp = new int[2 * n][2 * n];
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = A[i % n];
            preSum[i + 1] = preSum[i] + arr[i]; //节省空间，方便计算
            for (int j = 0; j < arr.length; j++) {
                if (i >= j) { //难点
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int val = search(i, i + n - 1);
            result = Math.min(result, val);
        }
        return result;
    }
    
    private int search(int left, int right) {
        if (dp[left][right] != Integer.MAX_VALUE) {
            return dp[left][right];
        }
        if (left >= right) {
            return dp[left][right];
        }
        
        for (int k = left; k <right; k++) {
            dp[left][right] = Math.min(dp[left][right], search(left, k) + search(k + 1, right) + preSum[right + 1] - preSum[left]);
        }
         
        return dp[left][right];
    }
}

/* 算法：区间类dp
         dp[i][j] = 在循环数组内，第i个石子到第j个石子的最小代价 
    时间复杂度：O(n^3) */