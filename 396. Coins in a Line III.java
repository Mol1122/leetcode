public class Solution {
    /**
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        int n = values.length;
        int[][] dp = new int[n + 1][n + 1];
        boolean[][] flag = new boolean[n + 1][n + 1];
        int sum = 0;
        
        for (int item : values) {
            sum += item;
        }
        return sum < 2 * searchMemory(0, values.length - 1, dp, flag, values);
    }
    
    private int searchMemory(int left, int right, int[][] dp, boolean[][] flag, int[] values) {
        if (flag[left][right]) {
            return dp[left][right];
        }
        flag[left][right] = true;
        if (left > right) {
            dp[left][right] = 0;
        } else if (left == right) {
            dp[left][right] = values[left];
        } else if (left + 1 == right) {
            dp[left][right] = Math.max(values[left], values[right]);
        } else {
            int  pick_left = Math.min(searchMemory(left + 2, right, dp, flag, values), searchMemory(left + 1, right - 1, dp, flag, values)) + values[left];
            int  pick_right = Math.min(searchMemory(left, right - 2, dp, flag, values), searchMemory(left + 1, right - 1, dp, flag, values)) + values[right];
            dp[left][right] = Math.max(pick_left, pick_right);   
        }
        return dp[left][right];
    }
}

/* 算法：博弈类dp
** 时间复杂度：O(n^2) 因为本质上是遍历一个二维数组 
** 难点：values[left] 和values[right] 要写在Math.min的外面 */