public class Solution {
    /**
     * @param A: An array of Integer
     * @return: an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int ans = 1, length = 1;
        
        //from left to right
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1]) {
                length++;
            } else {
                length = 1;
            }
            ans = Math.max(ans, length);
        }
        //from right to left
        length = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                length++;
                System.out.println("Yes");
            } else {
                length = 1;
            }
            ans = Math.max(ans, length);
        }
        return ans;
        
        // if (A == null || A.length == 0) {
        //     return 0;
        // }
        // int[] f = new int[A.length];
        // f[0] = 1;
        // for (int i = 1; i < A.length; i++) {
        //     if (A[i] > A[i - 1]) {
        //         f[i] = f[i - 1] + 1;
        //     } else {
        //         f[i] = 1;
        //     }
        // }
        
        // int[] dp = new int[A.length];
        // dp[A.length - 1] = 1;
        // for (int i = A.length - 2; i >= 0; i--) {
        //     if (A[i] >= A[i + 1]) {
        //         dp[i] = dp[i + 1] + 1;
        //     } else {
        //         dp[i] = 1;
        //     }
        // }
        // System.out.println(dp[0]);
        // return Math.max(f[A.length - 1], dp[0]);
    }
}

/* 算法：也可以用普通的坐标型动态规划去做题。
        f[i] = 以i为结尾的最长上升子序列有多长    
            */