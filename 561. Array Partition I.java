class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length; i += 2) {
            result += nums[i];
        }
        return result;
    }
}

/* 算法：思路很简单 需要将数组排序 然后找出每对数字的较小者
如min(nums[0], nums[1]), min(nums[2], nums[3]), min(nums[4], nums[5])

** 时间复杂度：O(n) */