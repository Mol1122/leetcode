class Solution {
    public int majorityElement(int[] nums) {
        int major = nums[0];
        int count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                count++;
                major = nums[i];
            } else if (major == nums[i]) {
                count++;
            } else {
                count--; //抵消之前的另一个数字的count
            }
        }
        return major;
    }
}

/* 算法：用count记录major个数。
** 难点：用count--来抵消之前另一个数的count,如果count为正，那么major一定是超过半数的 
** 时间复杂度：O(n)
** 空间复杂度：O(1) */