/* Given an array with positive and negative numbers, find the maximum average subarray which length should be greater or equal to given length k.

Example
Example 1:

Input:
[1,12,-5,-6,50,3]
3
Output:
15.667
Explanation:
 (-6 + 50 + 3) / 3 = 15.667
Example 2:

Input:
[5]
1
Output:
5.000
Notice
It's guaranteed that the size of the array is greater or equal to k. */

public class Solution {
    /**
     * @param nums: an array with positive and negative numbers
     * @param k: an integer
     * @return: the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0.0;
        }
        double start = -1e12, end = 1e12, eps = 1e-6;
        
        //猜max avarage是多少
        while (start + eps < end) {
            double mid = start + (end - start) / 2;
            if (check(nums, mid, k)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (check(nums, end, k)) {
            return end;
        }
        return start;
    }
    
    //判断有没有大于k个数的区间，满足avarage的要求的, 如果这段的区间和都>=0，说明真正的avg更大，那么说明，在这段区间内达到猜测的avarage是可以做到的,所以返回true去找更大的avarage
    private boolean check(int[] nums, double avg, int k) {
        double[] sums = new double[nums.length + 1];
        double min = 0, max = 0;
        
        for (int i = 0; i < k ; i++) {
            max += nums[i];
        }
        
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + (nums[i - 1] - avg);
            if (i >= k && sums[i] - min >= 0) {
                return true;
            }
            
            if (i >= k) {
                min = Math.min(min, sums[i - k + 1]);
            }
        }
        return false;
    }
}

/*
二分出 average 之后，把数组中的每个数都减去 average，然后的任务就是去求这个数组中，是否有长度 >= k 的 subarray，他的和超过 0。这一步用类似 Maximum Subarray 的解法来做就好了
eg. x1+x2+x3 / 3 = avg
    x1+x2+x3 = 3*avg
    (x1-avg) + (x2-avg) + (x3 - avg) = 0 =>真正的average
*/