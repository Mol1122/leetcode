class Solution {
    public int maxProduct(int[] nums) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        int result = nums[0];
        max[0] = min[0] = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            max[i] = min[i] = nums[i];
            if (nums[i] > 0) {
                max[i] = Math.max(max[i], max[i - 1] * nums[i]);
                min[i] = Math.min(min[i], min[i - 1] * nums[i]);
            } else {
                max[i] = Math.max(max[i], min[i - 1] * nums[i]);
                min[i] = Math.min(min[i], max[i - 1] * nums[i]);
            }
            result = Math.max(result, max[i]);
        }
        return result;
    }
}

/* 算法：用max和min数组去保存数组，这么做可以方便的对正负数进行想成，最后用result去track最大值是多少
** 时间复杂度：O(n) */