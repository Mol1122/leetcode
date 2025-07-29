/* Description
There are k sorted arrays nums. Find the median of the given k sorted arrays. */

public class Solution {
    /**
     * @param nums: the given k sorted arrays
     * @return: the median of the given k sorted arrays
     */
    public double findMedian(int[][] nums) {
        if (nums == null || nums.length == 0) {
            return 0.0;
        }
        int n = getTotal(nums);
        if (n == 0) { //易错，一定要单独列出来
            return 0;
        }
        if (n % 2 == 0) {
            return ((long)findKth(nums, n / 2) + (long)findKth(nums, n / 2 + 1)) / 2.0;
        }
        return findKth(nums, n / 2 + 1);
    }
    
    private int findKth(int[][] nums, int k) {
        int start = 0, end = Integer.MAX_VALUE;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (countSmallerOrEqual(nums, mid) < k) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (countSmallerOrEqual(nums, start) >= k) {
            return start;
        }
        return end;
    }
    
    private int countSmallerOrEqual(int[][] nums, int number) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += countSmallerOrEqual(nums[i], number);
        }
        return sum;
    }
    
    private int countSmallerOrEqual(int[] nums, int number) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0, end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= number) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] > number) {
            return start;
        }
        if (nums[end] > number) {
            return end;
        }
        return nums.length; //易错，一定是所有的数都小于等于它而不是
    }
    
    private int getTotal(int[][] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i].length;
        }
        return sum;
    }
}