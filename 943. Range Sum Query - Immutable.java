class NumArray {
    int[] prefixSum;

    public NumArray(int[] nums) {
        prefixSum = getPrefixSum(nums);
    }
    
    private int[] getPrefixSum(int[] nums) {
        int[] sum = new int[nums.length + 1];
        
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        return sum;
    }
    
    public int sumRange(int i, int j) {
        return prefixSum[j + 1] - prefixSum[i];
    }
}

/* 算法：prefixSum
** 时间复杂度：O(n) */

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */