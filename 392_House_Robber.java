public class Solution {
    /**
     * @param A: An array of non-negative integers
     * @return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        long[] result = new long[2];
        result[0] = 0;
        result[1] = A[0];
        for (int i = 2; i <= A.length; i++) {
            result[i%2] = Math.max(result[(i - 1)%2], result[(i - 2)%2] + A[i - 1]);
        }
        return result[A.length % 2];
    }
}

/* 实用滚动数组优化空间复杂度，dp[i]表示的是前i个选最优
** mod几取决于需要几个element的数组去保存值

* 面试一上来的时候写常规做法，然后稍加修改成滚动数组，面试官会眼前一亮*/