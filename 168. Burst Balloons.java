public class Solution {
    /**
     * @param nums: A list of integer
     * @return: An integer, maximum coins
     */
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        boolean[][] visited = new boolean[n + 2][n + 2];
        int[] arr = new int[n + 2];
        for (int i = 0; i < n; i++) {
            arr[i + 1] = nums[i];
        }
        arr[0] = 1;
        arr[n + 1] = 1;
        
        return search(arr, dp, visited, 1, n); 
    }
    
    private int search(int[] arr, int[][] dp, boolean[][] visited, int left, int right) {
        if (visited[left][right]) { //记忆化搜索
            return dp[left][right];
        }
        int result = 0;
        for (int k = left; k <= right; k++) {
            //选中第k个最后一个打爆，其实[i,j]之间都已经被打爆了的，所以左右的值为left-1, right+1
            int midValue = arr[left - 1] * arr[k] * arr[right + 1];
            int leftValue = search(arr, dp, visited, left, k - 1);
            int rightValue = search(arr, dp, visited, k + 1, right);
            result = Math.max(result, leftValue + rightValue + midValue);
        }
        visited[left][right] = true;
        dp[left][right] = result;
        return result;
    }
}

/* 算法：区间类dp, 从大往小搜索，这样方便记忆化搜索。
**       state: dp[i][j]表示第i到第j个气球打爆的最大值
**       function: midValue = arr[i-1] * arr[k] * arr[j+1];
                   dp[i][j] = max(dp[i][k-1]+dp[k+1][j]+midvalue)
         initialization: dp[i][i] = arr[i-1] * arr[i] * arr[i+1]; 
         ans: dp[0][n-1] 
    时间复杂度：O(n^3) 
    难点：minValue是如何计算的，记住因为是从大往小搜索，到区间[i,j]的时候整个区间都应该是已经打爆了的  */