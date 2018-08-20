public class Solution {
    /**
     * @param nums: An integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] minLast = new int[nums.length + 1];
        minLast[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            minLast[i] = Integer.MAX_VALUE;
        }
        
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
    
    //find the 1st number > num
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
        if (minLast[start] > num) {
            return start;
        }
        return end;
    }
}

/* 算法：minLast数组实际上记录上升的子序列，minLast = min是为了不越界。遍历每个数，在minLast里面找到第一个比他大的数，然后替换掉(只有替换掉才能保证上升趋势)
** 时间复杂度：用binary search的方法实现O(nlogn)的时间复杂度。  
** 空间复杂度：O(n) */