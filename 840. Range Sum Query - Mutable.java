class NumArray {
    private int[] arr, bit;

    public NumArray(int[] nums) {
        arr = new int[nums.length];
        bit = new int[nums.length + 1];
        
        for (int i = 0; i < nums.length; i++) {
            update(i, nums[i]);
        }
    }
    
    public void update(int index, int val) {
        int delta = val - arr[index];
        arr[index] = val;
        
        for (int i = index + 1; i <= arr.length; i = i + lowbit(i)) {
            bit[i] += delta;
        }
    }
    
    private int lowbit(int x) {
        return x & (-x);
    }
    
    private int getPrefixSum(int index) {
        int sum = 0;
        
        for (int i = index + 1; i > 0; i = i - lowbit(i)) {
            sum += bit[i];
        }
        return sum;
    }
    
    public int sumRange(int i, int j) {
        return getPrefixSum(j) - getPrefixSum(i - 1);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */