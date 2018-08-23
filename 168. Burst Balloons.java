public class Solution {
    /**
     * @param nums: A list of integer
     * @return: An integer, maximum coins
     */
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        nums = init(nums);
        int n = nums.length;
        int[][] f = new int[n][n];
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                for (int k = i + 1; k < j; k++) {
                    f[i][j] = Math.max(f[i][j], f[i][k] + f[k][j] + nums[i] * nums[k] * nums[j]);
                }
            }
        }
        return f[0][n - 1];
    }
    
    private int[] init(int[] nums) {
        int[] temp = new int[nums.length + 2];
        
        temp[0] = 1;
        for (int i = 1; i < temp.length - 1; i++) {
            temp[i] = nums[i - 1];
        }
        temp[temp.length - 1] = 1;
        
        return temp;
    }
}

/* 算法：区间型动态规划，非常典型的逆向思维，从最后一步思考，之后变成子区间
** f[i][j] 代表 burst i+1 ~ j-1 这段时间的所有气球之后，只剩下 i,j 的最大收益。*/