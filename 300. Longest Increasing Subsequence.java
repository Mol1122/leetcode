class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] minLast = new int[nums.length + 1];
        minLast[0] = Integer.MIN_VALUE; //因为是算长度而不是index，就忽略0这个index
        for (int i = 1; i < nums.length + 1; i++) {
            minLast[i] = Integer.MAX_VALUE;
        }
        //find the first minLast >= nums[i]
        for (int i = 0; i < nums.length; i++) {
            int index = binarySearch(minLast, nums[i]);
            minLast[index] = nums[i];
        }
        for (int i = nums.length; i >= 0; i--) {
            if (minLast[i] != Integer.MAX_VALUE) {
                return i;
            }
        }
        return 0;
    }
    
    private int binarySearch(int[] minLast, int num) {
        int start = 0, end = minLast.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (minLast[mid] < num) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (minLast[start] >= num) {
            return start;
        }
        return end;
    }
}

/* 算法：本质上是动态规划，但是可以用binary search去达到logn的时间复杂度. minLast[i]画个图就很容易理解了
** 时间复杂度：O(nlogn) */