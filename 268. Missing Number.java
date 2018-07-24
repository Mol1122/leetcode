class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length, i = 0;
        
        while (i < n) {
            while (nums[i] != i && nums[i] < n) {
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
            i++;
        }
        for (i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;
    }
}

/* 算法：两层for循环就能把所有该在index位置上的放回位置上, 并且不占用格外空间
** 时间复杂度：O(n^2) */