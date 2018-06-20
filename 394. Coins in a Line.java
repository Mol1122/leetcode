public class Solution {
    /**
     * @param n: An integer
     * @return: A boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        int[] dp = new int[n + 1];
        return MemorySearch(n, dp);
    }
    
    private boolean MemorySearch(int n, int[] dp) {
        if (dp[n] != 0) {
            if (dp[n] == 2) {
                return true;
            } else {
                return false;
            }
        }
        if (n <= 0) { //0 empty, 1 false, 2 true
            dp[n] = 1;
        } else if (n == 1) {
            dp[n] = 2;
        } else if (n == 2) {
            dp[n] = 2;
        } else if (n == 3) {
            dp[n] = 1;
        } else {
            if((MemorySearch(n-2, dp) && MemorySearch(n-3, dp)) || 
                (MemorySearch(n-3, dp) && MemorySearch(n-4, dp) )) {
                dp[n] = 2;
            } else {
                dp[n] = 1;
            }
        }
        if (dp[n] == 2) {
            return true;
        } else {
            return false;
        }
    }
}

/* 算法：博弈类dp.
   state: dp[i]现在还剩i个硬币，现在“先手”取硬币的人最后的输赢状况
   function：do[i] = (dp[1-2] && dp[i-3]) || (dp[i-3] && dp[i-4])     (1/1, 1/2, 2/1, 2/2)
   initialize:dp[0] = false, dp[1] = true, dp[2] = true, dp[3] = false
   answer: dp[n] 
   时间复杂度：O(n) */