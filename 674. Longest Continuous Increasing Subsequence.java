class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length==0) return 0;
        int length=0,tmp=1;
        for(int i=0; i<nums.length-1;i++) {
            if(nums[i]<nums[i+1]) {tmp++; length=Math.max(length,tmp);}
            if(nums[i]>=nums[i+1]) tmp=1; 
        }
        return Math.max(length,1);
    }
}

/* 算法：普通dp */
