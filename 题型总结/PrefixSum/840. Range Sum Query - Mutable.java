/* Given an integer array nums, and then you need to implement two functions:

update(i, val) Modify the element whose index is i to val.
sumRange(l, r) Return the sum of elements whose indexes are in range of [l, r][l,r].
Example
Example 1:

Input: 
  nums = [1, 3, 5]
  sumRange(0, 2)
  update(1, 2)
  sumRange(0, 2)
Output:
  9
  8
Example 2:

Input:
  nums = [0, 9, 5, 7, 3]
  sumRange(4, 4)
  sumRange(2, 4)
  update(4, 5)
  update(1, 7)
  update(0, 8)
  sumRange(1, 2)
Output:
  3
  15
  12
Notice
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly. */

class NumArray {
    int[] arr, bits;

    public NumArray(int[] nums) {
        arr = new int[nums.length];
        bits = new int[nums.length + 1];
        
        for (int i = 0; i < nums.length; i++) {
            update(i, nums[i]);
        }
    }
    
    public void update(int index, int val) {
        int delta = val - arr[index];
        arr[index] = val;
        
        for (int i = index + 1; i <= arr.length; i += lowbit(i)) {
            bits[i] += delta;
        }
    }
    
    private int lowbit(int x) {
        return x & (-x);
    }
    
    private int getPrefixSum(int index) {
        int sum = 0;
        for (int i = index + 1; i > 0; i -= lowbit(i)) {
            sum += bits[i];     
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