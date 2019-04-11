class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        discretization(nums);
        int[] bit = new int[nums.length + 1];
        int[] count = new int[nums.length];
        
        for (int i = nums.length - 1; i >= 0; i--) {
            count[i] = getSum(bit, nums[i] - 1); //查看比第几小的数有几个
            update(bit, nums[i]); //存在BIT的值是第一小的数，第二小的数，...
        }
        
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < count.length; i++) {
            result.add(count[i]);
        }
        
        return result;
    }
    
     private void update(int[] bit, int index) {
        for (int i = index + 1; i < bit.length; i = i + lowbit(i)) {
            bit[i]++;
        }
    }
    
    private int getSum(int[] bit, int index) {
        int sum = 0;
        for (int i = index + 1; i > 0; i = i - lowbit(i)) {
            sum += bit[i];
        }
        return sum;
    }
    
    private int lowbit(int x) {
        return x & (-x);
    }
    
    private void discretization(int[] nums) {
        int[] copy = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            copy[i] = nums[i];
        }
        Arrays.sort(copy);
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Arrays.binarySearch(copy, nums[i]) + 1;
        }
    }
}

/* 时间复杂度：O(nlogn)
** 空间复杂度：O(n) */

